package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IEmployAO;
import com.cdkj.gchf.bo.IAttendanceBO;
import com.cdkj.gchf.bo.IEmployBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IReportBO;
import com.cdkj.gchf.bo.IStaffBO;
import com.cdkj.gchf.bo.IStaffLogBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.AmountUtil;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.Attendance;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.Report;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631460Req;
import com.cdkj.gchf.dto.req.XN631461Req;
import com.cdkj.gchf.enums.EAttendanceStatus;
import com.cdkj.gchf.enums.EEmploytatus;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EProjectStatus;
import com.cdkj.gchf.enums.EUser;
import com.cdkj.gchf.exception.BizException;

@Service
public class EmployAOImpl implements IEmployAO {

    @Autowired
    private IEmployBO employBO;

    @Autowired
    private IProjectBO projectBO;

    @Autowired
    private IStaffBO staffBO;

    @Autowired
    private IStaffLogBO staffLogBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IReportBO reportBO;

    @Autowired
    private IAttendanceBO attendanceBO;

    @Override
    public String joinIn(XN631460Req req) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Employ.getCode());
        employBO.isExist(req.getProjectCode(), req.getStaffCode());
        Staff staff = staffBO.getStaff(req.getStaffCode());

        Employ data = new Employ();
        data.setCode(code);
        Project project = projectBO.getProject(req.getProjectCode());
        data.setProjectCode(req.getProjectCode());
        if (EProjectStatus.End.getCode().equals(project.getStatus())
                || EProjectStatus.Stop.getCode().equals(project.getStatus())) {
            throw new BizException("xn0000", "该项目已经结束");
        }
        data.setCompanyCode(project.getCompanyCode());
        data.setCompanyName(project.getCompanyName());
        data.setProjectName(project.getName());

        data.setStaffCode(staff.getCode());

        data.setStaffMobile(staff.getMobile());
        data.setType(req.getType());
        data.setPosition(req.getPosition());
        data.setSalary(StringValidater.toLong(req.getSalary()));
        data.setCutAmount(StringValidater.toLong(req.getCutAmount()));

        userBO.getUser(req.getUpUser());
        data.setUpUser(req.getUpUser());
        data.setJoinDatetime(DateUtil.strToDate(req.getJoinDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setStatus(EEmploytatus.Work.getCode());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        employBO.joinIn(data);
        System.out.println("======入职结束=========");
        // 计入累积入职
        Report report = reportBO.getReportByProject(project.getCode());
        Long nextMonthSalary = StringValidater.toLong(req.getSalary())
                / DateUtil.getMonthDays() * DateUtil.getRemainDays()
                + report.getNextMonthSalary();
        report.setNextMonthSalary(nextMonthSalary);
        reportBO.staffIn(report);
        System.out.println("==========统计结束=========");
        // 生成考勤
        Attendance attendance = new Attendance();
        String attendanceCode = OrderNoGenerater
            .generate(EGeneratePrefix.Attendance.getCode());
        attendance.setCode(attendanceCode);
        attendance.setProjectCode(project.getCode());
        attendance.setProjectName(project.getName());

        attendance.setStaffCode(staff.getCode());
        attendance.setStaffMobile(staff.getMobile());

        attendance.setStatus(EAttendanceStatus.TO_Start.getCode());
        Date date = new Date();
        attendance.setCreateDatetime(date);
        attendanceBO.saveAttendance(attendance);
        System.out.println("=======考勤生成结束========");
        staffLogBO.saveStaffLog(data, staff.getName(), project.getCompanyCode(),
            project.getCompanyName(), project.getCode(), project.getName());
        System.out.println("=========历史记录结束======");
        return code;
    }

    @Override
    public void toHoliday(XN631461Req req) {

        Employ data = employBO.getEmploy(req.getCode());
        if (!EEmploytatus.Work.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该员工不是在职状态");
        }
        Date start = DateUtil.strToDate(req.getStartDatetime(),
            DateUtil.DATA_TIME_PATTERN_1);
        Date end = DateUtil.strToDate(req.getEndDatetime(),
            DateUtil.DATA_TIME_PATTERN_1);
        if (start == null || end == null) {
            throw new BizException("xn0000", "时间格式不正确");
        }
        if (start.after(end)) {
            throw new BizException("xn0000", "开始时间不能大于结束时间");
        }

        // 统计请假人数
        Report report = reportBO.getReportByProject(data.getProjectCode());
        report.setLeavingDays(report.getLeavingDays() + 1);
        reportBO.refreshLeavingDays(report);

        Project project = projectBO.getProject(data.getProjectCode());
        // 统计请假天数
        double leadingDays = data.getLeavingDays()
                + DateUtil.getDays(project.getAttendanceStarttime(),
                    project.getAttendanceEndtime(), start, end);
        data.setLeavingDays(leadingDays);

        // 累积请假天数
        data.setTotalLeavingDays(data.getTotalLeavingDays() + leadingDays);
        data.setStartDatetime(start);
        data.setEndDatetime(end);
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());

        data.setRemark(req.getRemark());
        employBO.toHoliday(data);

    }

    @Override
    public void leaveOffice(String code, String leavingDatetime, String updater,
            String remark) {
        Employ data = employBO.getEmploy(code);
        if (EEmploytatus.Leave.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该员工已经离职");
        }
        employBO.leaveOffice(data, leavingDatetime, updater, remark);
        // 统计累积离职人数
        Report report = reportBO.getReportByProject(data.getProjectCode());
        report.setStaffOut(report.getStaffOut() + 1);
        reportBO.refreshStaffOut(report);

        // 更新目前在职人数
        report.setStaffOn(report.getStaffOn() - 1);
        reportBO.refreshStaffOn(report);

        // 修改下月预发工资
        long daySalay = data.getSalary() / DateUtil.getMonthDays();
        report.setNextMonthSalary(report.getNextMonthSalary()
                - AmountUtil.mul(daySalay, data.getLeavingDays()));
        reportBO.refreshNextMonthSalary(report);

        Project project = projectBO.getProject(data.getCode());
        staffLogBO.saveStaffLog(data, data.getStaffCode(),
            project.getCompanyCode(), project.getCompanyName(),
            project.getCode(), project.getName());

    }

    @Override
    public int editEmploy(Employ data) {
        return employBO.refreshEmploy(data);
    }

    @Override
    public int dropEmploy(String code) {
        return employBO.removeEmploy(code);
    }

    @Override
    public Paginable<Employ> queryEmployPage(int start, int limit,
            Employ condition) {
        System.out.println(
            condition.getStaffCode() + "," + condition.getProjectCodeList());
        Paginable<Employ> page = employBO.getPaginable(start, limit, condition);
        List<Employ> list = page.getList();
        for (Employ employ : list) {
            Staff staff = staffBO.getStaff(employ.getStaffCode());
            employ.setStaff(staff);
            employ.setUpUserName(getName(employ.getUpUser()));
            employ.setUpdateName(getName(employ.getUpdater()));
            employ.setStaffName(staff.getName());
        }
        page.setList(list);
        return page;
    }

    @Override
    public List<Employ> queryEmployList(Employ condition) {
        // 补充信息
        List<Employ> list = employBO.queryEmployList(condition);
        for (Employ employ : list) {
            Staff staff = staffBO.getStaff(employ.getStaffCode());
            employ.setStaff(staff);
            employ.setStaff(staff);
            employ.setUpUserName(getName(employ.getUpUser()));
            employ.setUpdateName(getName(employ.getUpdater()));
            employ.setStaffName(staff.getName());
        }
        return list;
    }

    @Override
    public Employ getEmploy(String code) {
        Employ data = employBO.getEmploy(code);
        Staff staff = staffBO.getStaff(data.getStaffCode());
        data.setStaff(staff);
        String upUesrName = getName(data.getUpUser());
        String updateName = getName(data.getUpdater());
        data.setUpUserName(upUesrName);
        data.setUpdateName(updateName);
        return data;
    }

    private String getName(String userId) {
        User user = userBO.getUserName(userId);
        String name = EUser.ADMIN.getCode();
        if (!EUser.ADMIN.getCode().equals(user.getLoginName())) {
            name = user.getRealName();
        }
        return name;

    }

    public void updateStatus() {
        Employ eCondition = new Employ();
        eCondition.setStatus(EEmploytatus.Not_Leave.getCode());
        List<Employ> eList = employBO.queryEmployList(eCondition);
        for (Employ employ : eList) {
            String status = EEmploytatus.Work.getCode();
            // 今天是否请假
            if (DateUtil.isIn(employ.getStartDatetime(),
                employ.getEndDatetime())) {
                status = EEmploytatus.Hoilday.getCode();
            }
            employ.setStatus(status);
            employBO.updateStatus(employ);
        }

    }

}
