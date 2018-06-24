package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IEmployAO;
import com.cdkj.gchf.bo.IAttendanceBO;
import com.cdkj.gchf.bo.ICcontractBO;
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
import com.cdkj.gchf.domain.Attendance;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.Report;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631460Req;
import com.cdkj.gchf.dto.req.XN631461Req;
import com.cdkj.gchf.dto.req.XN631462Req;
import com.cdkj.gchf.enums.EAttendanceStatus;
import com.cdkj.gchf.enums.EEmploytatus;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EProjectStatus;
import com.cdkj.gchf.enums.EStaffSalaryStatus;
import com.cdkj.gchf.enums.EUser;
import com.cdkj.gchf.exception.BizException;

@Service
public class EmployAOImpl implements IEmployAO {

    @Autowired
    IEmployBO employBO;

    @Autowired
    IProjectBO projectBO;

    @Autowired
    IStaffBO staffBO;

    @Autowired
    IStaffLogBO staffLogBO;

    @Autowired
    IUserBO userBO;

    @Autowired
    IReportBO reportBO;

    @Autowired
    IAttendanceBO attendanceBO;

    @Autowired
    ICcontractBO ccontractBO;

    @Override
    public String joinIn(XN631460Req req) {
        Employ data = new Employ();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Employ.getCode());
        Staff staff = staffBO.getStaff(req.getStaffCode());
        Date date = new Date();
        data.setCode(code);

        Project project = projectBO.getProject(req.getProjectCode());
        if (!EProjectStatus.Building.getCode().equals(project.getStatus())) {
            throw new BizException("xn0000", "该项目未通过审核或已停工");
        }
        data.setProjectCode(req.getProjectCode());

        data.setProjectName(project.getName());
        data.setStaffCode(staff.getCode());
        data.setStaffName(staff.getName());
        data.setStaffMobile(staff.getMobile());
        data.setType(req.getType());

        // data.setPosition(req.getPosition());
        // data.setSalary(StringValidater.toLong(req.getSalary()));
        // data.setCutAmount(StringValidater.toLong(req.getCutAmount()));
        // data.setJoinDatetime(DateUtil.strToDate(req.getJoinDatetime(),
        // DateUtil.FRONT_DATE_FORMAT_STRING));

        data.setStatus(EEmploytatus.Work.getCode());
        data.setSalaryStatus(EStaffSalaryStatus.Normal.getCode());
        // data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(date);
        // data.setRemark(req.getRemark());

        Employ checkData = employBO.getEmployByStaff(req.getStaffCode(),
            req.getProjectCode());
        // 防止重复办理入职
        if (checkData != null) {
            if (!EEmploytatus.Leave.getCode().equals(data.getStatus())) {
                throw new BizException("xn00000", "该员工已入职该项目，请勿重复办理入职");
            }
            data.setCode(checkData.getCode());
            data.setStatus(EEmploytatus.Work.getCode());
            employBO.refreshEmploy(data);
        }
        employBO.joinIn(data);

        // 录入合同
        // Ccontract ccontract = new Ccontract();
        // String ccontractCode = OrderNoGenerater
        // .generate(EGeneratePrefix.Ccontract.getCode());
        // ccontract.setProjectCode(req.getProjectCode());
        //
        // ccontract.setProjectName(project.getName());
        // ccontract.setStaffCode(staff.getCode());
        // ccontract.setStaffName(staff.getName());
        // ccontract.setContentPic(req.getContentPic());
        // ccontract.setContractDatetime(DateUtil.strToDate(
        // req.getContractDatetime(), DateUtil.FRONT_DATE_FORMAT_STRING));
        //
        // ccontract.setUpdater(req.getUpdater());
        // ccontract.setUpdateDatetime(date);
        // ccontract.setRemark(req.getRemark());

        // Ccontract checkCcontract = ccontractBO.isExist(req.getProjectCode(),
        // req.getStaffCode());
        // if (checkCcontract != null) {
        // ccontract.setCode(checkCcontract.getCode());
        // ccontractBO.refreshCcontract(ccontract);
        // }
        // ccontract.setCode(ccontractCode);
        // ccontractBO.saveCcontract(ccontract);

        // 计入累积入职
        Report report = reportBO.getReportByProject(project.getCode());

        Long nextMonthSalary = AmountUtil.mul(data.getSalary(),
            DateUtil.getMonthDays()) + report.getNextMonthSalary();
        report.setNextMonthSalary(nextMonthSalary);
        report.setStaffOn(report.getStaffOn() + 1);
        report.setStaffIn(report.getStaffIn() + 1);
        reportBO.staffIn(report);

        // 生成考勤
        Attendance attendance = new Attendance();

        String attendanceCode = OrderNoGenerater
            .generate(EGeneratePrefix.Attendance.getCode());
        attendance.setCode(attendanceCode);
        attendance.setProjectCode(project.getCode());
        attendance.setProjectName(project.getName());

        attendance.setStaffCode(staff.getCode());
        attendance.setStaffName(staff.getName());
        attendance.setStaffMobile(staff.getMobile());
        attendance.setStatus(EAttendanceStatus.TO_Start.getCode());
        attendance.setCreateDatetime(date);

        attendanceBO.saveAttendance(attendance);
        staffLogBO.saveStaffLog(data, staff.getName(), project.getCompanyCode(),
            project.getCode(), project.getName());
        return code;
    }

    @Override
    public void toHoliday(XN631461Req req) {
        Employ data = employBO.getEmployByStaff(req.getStaffCode(),
            req.getProjectCode());
        if (EEmploytatus.Work.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该员工已离职");
        }
        if (EEmploytatus.Hoilday.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该员工已在请假中");
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
    @Transactional
    public void leaveOffice(XN631462Req req) {
        Employ data = employBO.getEmployByStaff(req.getStaffCode(),
            req.getProjectCode());
        if (data == null) {
            throw new BizException("xn0000", "该员工未在该项目任职");
        }
        if (EEmploytatus.Leave.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该员工已经离职");
        }
        employBO.leaveOffice(data, req.getLeavingDatetime(), req.getUpdater(),
            req.getRemark());
        // 统计累积离职人数
        Report report = reportBO.getReportByProject(data.getProjectCode());
        report.setStaffOut(report.getStaffOut() + 1);
        reportBO.refreshStaffOut(report);

        // 更新目前在职人数
        report.setStaffOn(report.getStaffOn() - 1);
        reportBO.refreshStaffOn(report);

        // 修改下月预发工资
        report.setNextMonthSalary(report.getNextMonthSalary()
                - AmountUtil.mul(data.getSalary(), DateUtil.getMonthDays()));
        reportBO.refreshNextMonthSalary(report);

        Project project = projectBO.getProject(data.getProjectCode());
        staffLogBO.saveStaffLog(data, data.getStaffCode(),
            project.getCompanyCode(), project.getCode(), project.getName());

    }

    @Override
    public void editEmploy(Employ data) {
        employBO.refreshEmploy(data);
    }

    @Override
    public int dropEmploy(String code) {
        return employBO.removeEmploy(code);
    }

    @Override
    public Paginable<Employ> queryEmployPage(int start, int limit,
            Employ condition) {
        Paginable<Employ> page = employBO.getPaginable(start, limit, condition);
        List<Employ> list = page.getList();
        for (Employ employ : list) {
            Staff staff = staffBO.getStaff(employ.getStaffCode());
            employ.setStaff(staff);

            employ.setUpUserName(getName(employ.getUpUser()));
            employ.setUpdateName(getName(employ.getUpdater()));
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
        Project project = null;
        for (Employ employ : eList) {
            String status = EEmploytatus.Work.getCode();
            project = projectBO.getProject(employ.getCode());
            // 今天是否请假
            if (employ.getStartDatetime() != null
                    && employ.getEndDatetime() != null) {
                if (DateUtil.isIn(employ.getStartDatetime(),
                    employ.getEndDatetime())) {
                    status = EEmploytatus.Hoilday.getCode();
                    // 请假时间是否跨月
                    Date endDatetime = employ.getEndDatetime();
                    if (DateUtil.isIn(employ.getStartDatetime(),
                        DateUtil.getLastDay(), employ.getEndDatetime())) {
                        endDatetime = DateUtil.getLastDay();
                    }
                    // 计算请假时间
                    double leadingDays = employ.getLeavingDays()
                            + DateUtil.getDays(project.getAttendanceStarttime(),
                                project.getAttendanceEndtime(),
                                employ.getStartDatetime(), endDatetime);
                    employ.setLeavingDays(leadingDays);
                }
            }
            employ.setStatus(status);
            employBO.updateStatus(employ);
        }

    }

}
