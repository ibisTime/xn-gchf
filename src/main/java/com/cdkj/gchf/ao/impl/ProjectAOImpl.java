package com.cdkj.gchf.ao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IProjectAO;
import com.cdkj.gchf.bo.IAttendanceBO;
import com.cdkj.gchf.bo.ICompanyBO;
import com.cdkj.gchf.bo.ICompanyCardBO;
import com.cdkj.gchf.bo.IEmployBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IReportBO;
import com.cdkj.gchf.bo.ISalaryBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.Attendance;
import com.cdkj.gchf.domain.Company;
import com.cdkj.gchf.domain.CompanyCard;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.Report;
import com.cdkj.gchf.domain.Salary;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631350Req;
import com.cdkj.gchf.dto.req.XN631352Req;
import com.cdkj.gchf.dto.req.XN631353Req;
import com.cdkj.gchf.enums.EAttendanceStatus;
import com.cdkj.gchf.enums.EBoolean;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EProjectStatus;
import com.cdkj.gchf.enums.ESalaryStatus;
import com.cdkj.gchf.enums.EStaffStatus;
import com.cdkj.gchf.enums.EUser;
import com.cdkj.gchf.exception.BizException;

@Service
public class ProjectAOImpl implements IProjectAO {

    @Autowired
    private IProjectBO projectBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ICompanyCardBO companyCardBO;

    @Autowired
    private ICompanyBO companyBO;

    @Autowired
    private IAttendanceBO attendanceBO;

    @Autowired
    private IEmployBO employBO;

    @Autowired
    private IReportBO reportBO;

    @Autowired
    private ISalaryBO salaryBO;

    @Override
    public String addProject(XN631350Req req) {
        Project data = new Project();

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Project.getCode());
        data.setCode(code);
        data.setCompanyCode(req.getCompanyCode());
        Company company = companyBO.getCompany(req.getCompanyCode());
        data.setCompanyName(company.getName());
        data.setName(req.getName());
        data.setChargeUser(req.getChargeUser());
        User user = userBO.getUser(req.getChargeUser());
        if (StringUtils.isNotBlank(user.getMobile())) {
            data.setChargeMobile(user.getMobile());
        }

        data.setStartDatetime(DateUtil.strToDate(req.getStartDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));

        data.setLongitude(req.getLongitude());
        data.setLatitude(req.getLatitude());
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());

        data.setArea(req.getArea());
        data.setAddress(req.getAddress());
        data.setSalaryCreateDatetime(req.getSalaryCreateDatetime());
        data.setSalaryDatetime(req.getSalaryDatetime());
        data.setAttendanceStarttime(req.getAttendanceStarttime());
        data.setAttendanceEndtime(req.getAttendanceEndtime());

        data.setStatus(EProjectStatus.TO_AUDIT_NO.getCode());
        data.setUpdater(req.getUpdater());

        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        projectBO.saveProject(data);
        // 添加公司账户

        companyCardBO.saveCompanyCard(code, req.getName(), company.getCode(),
            company.getName(), req.getBankCode(), req.getBankName(),
            req.getBankcardNumber(), req.getSubbranch(), req.getUpdater(),
            data.getUpdateDatetime(), req.getRemark());
        return code;
    }

    @Override
    public void editProject(XN631352Req req) {
        Project data = projectBO.getProject(req.getCode());
        data.setName(req.getName());

        User user = userBO.getUser(req.getChargeUser());
        data.setChargeUser(req.getChargeUser());
        if (StringUtils.isNotBlank(user.getMobile())) {
            data.setChargeMobile(user.getMobile());
        }
        data.setStartDatetime(DateUtil.strToDate(req.getStartDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setLongitude(req.getLongitude());
        data.setLatitude(req.getLatitude());
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());
        data.setArea(req.getArea());

        data.setAddress(req.getAddress());
        data.setSalaryCreateDatetime(req.getSalaryCreateDatetime());
        data.setSalaryDatetime(req.getSalaryDatetime());
        data.setAttendanceStarttime(req.getAttendanceStarttime());
        data.setAttendanceEndtime(req.getAttendanceEndtime());

        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        projectBO.editProject(data);
    }

    @Override
    public Paginable<Project> queryProjectPage(int start, int limit,
            Project condition) {
        Paginable<Project> page = projectBO.getPaginable(start, limit,
            condition);
        List<Project> list = page.getList();
        String approveName = null;
        String updateName = null;
        for (Project project : list) {
            approveName = getName(project.getApprover());
            updateName = getName(project.getUpdater());
            project.setApproveName(approveName);
            project.setUpdateName(updateName);
        }
        return page;
    }

    @Override
    public Project getProject(String code) {
        Project data = projectBO.getProject(code);
        CompanyCard companyCard = companyCardBO
            .getCompanyCardByProject(data.getCode());
        data.setCompanyCard(companyCard);
        Report report = reportBO.getReportByProject(data.getCode());
        data.setReport(report);
        // 补全名字信息
        String approveName = getName(data.getApprover());
        String updateName = getName(data.getUpdater());
        data.setApproveName(approveName);
        data.setUpdateName(updateName);
        return data;
    }

    @Override
    public List<Project> queryProjectList(Project condition) {

        String approveName = null;
        String updateName = null;
        List<Project> list = projectBO.queryProject(condition);
        for (Project project : list) {
            approveName = getName(project.getApprover());
            updateName = getName(project.getUpdater());
            project.setApproveName(approveName);
            project.setUpdateName(updateName);
        }
        return list;
    }

    @Override
    public void toApprove(XN631353Req req) {
        Project data = projectBO.getProject(req.getCode());
        if (!EProjectStatus.TO_AUDIT_NO.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "项目不处于待提请审核状态");
        }

        data.setName(req.getName());
        User user = userBO.getUser(req.getChargeUser());
        data.setChargeUser(req.getChargeUser());
        if (StringUtils.isNotBlank(user.getMobile())) {
            data.setChargeMobile(user.getMobile());
        }
        data.setStartDatetime(DateUtil.strToDate(req.getStartDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setLongitude(req.getLongitude());
        data.setLatitude(req.getLatitude());
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());
        data.setArea(req.getArea());

        data.setAddress(req.getAddress());
        data.setSalaryCreateDatetime(req.getSalaryCreateDatetime());
        data.setSalaryDatetime(req.getSalaryDatetime());
        data.setAttendanceStarttime(req.getAttendanceStarttime());
        data.setAttendanceEndtime(req.getAttendanceEndtime());

        data.setStatus(EProjectStatus.TO_AUDIT_YES.getCode());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        projectBO.toApprove(data);

    }

    @Override
    public void approveProject(String code, String result, String approver,
            String approveNote) {
        Project data = projectBO.getProject(code);
        if (!EProjectStatus.TO_AUDIT_YES.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该工程项目未处于待审核状态");
        }
        String status = EProjectStatus.PASS.getCode();
        if (EBoolean.NO.getCode().equals(result)) {
            status = EProjectStatus.TO_AUDIT_NO.getCode();
        }

        data.setStatus(status);
        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        if (StringUtils.isBlank(approveNote)) {
            approveNote = "您申请的项目[" + data.getName() + "]未通过审核,请在修改后再次申请";
        }
        data.setApproveNote(approveNote);
        projectBO.approveProject(data);
    }

    @Override
    public void projectEnd(String code, String endDatetime, String updater,
            String remark) {
        Project data = projectBO.getProject(code);
        if (!EProjectStatus.Building.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该工程项目未处于在建状态");
        }
        if (DateUtil.strToDate(endDatetime, DateUtil.FRONT_DATE_FORMAT_STRING)
            .before(new Date())) {
            throw new BizException("xn0000", "请输入正确的结束时间");
        }

        projectBO.projectEnd(data, endDatetime, updater, remark);
    }

    // 定时器形成工资条
    public void createSalary() {

        // 判断项目是否开始
        Project condition = new Project();
        condition.setStatus(EProjectStatus.Building.getCode());
        List<Project> list = projectBO.queryProject(condition);
        for (Project project : list) {
            Date start = project.getStartDatetime();
            Date Date = DateUtil.getTodayStart();
            if (Date.after(start)) {
                project.setStatus(EProjectStatus.Building.getCode());
                projectBO.toBuilding(project);
            }
        }

        Date date = new Date();
        // 获取已经开始的项目
        condition.setStatus(EProjectStatus.Building.getCode());
        List<Project> pList = projectBO.queryProject(condition);

        for (Project project : pList) {

            // 获取项目的雇佣关系
            Employ eCondition = new Employ();
            eCondition.setProjectCode(project.getCode());
            List<Employ> eList = employBO.queryEmployList(eCondition);

            // 若当前时间是工资条生成时间,形成工资条
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            if (day == StringValidater
                .toInteger(project.getSalaryCreateDatetime())) {
                // 生成工资条
                for (Employ employ : eList) {
                    Salary data = new Salary();
                    String code = OrderNoGenerater
                        .generate(EGeneratePrefix.Salary.getCode());
                    data.setCode(code);
                    data.setProjectCode(project.getCode());
                    data.setStaffCode(employ.getStaffCode());
                    data.setMonth(calendar.get(Calendar.MONTH));
                    data.setShouldAmount(employ.getSalary());

                    data.setCreateDatetime(date);
                    data.setStatus(ESalaryStatus.To_Approve.getCode());

                    // 统计上个月工人考勤
                    Date startDatetime = DateUtil
                        .getFristDay(DateUtil.getMonth() - 1);
                    Date endDatetime = DateUtil
                        .getLastDay(DateUtil.getMonth() - 1);
                    Attendance aCondition = new Attendance();
                    aCondition.setCreateDatetimeStart(startDatetime);
                    aCondition.setCreateDatetimeEnd(endDatetime);
                    aCondition.setStatus(EAttendanceStatus.Unpaied.getCode());
                    List<Attendance> aList = attendanceBO
                        .queryAttendanceList(aCondition);

                    // 计算上月迟到和早退天数
                    int early = 0;
                    int delay = 0;

                    for (Attendance attendance : aList) {
                        // 迟到
                        if (StringUtils
                            .isNotBlank(attendance.getStartDatetime())
                                && DateUtil.compare(
                                    attendance.getStartDatetime(),
                                    project.getAttendanceStarttime(),
                                    DateUtil.FRONT_DATE_FORMAT_STRING)) {
                            early = early + 1;
                        }
                        // 早退
                        if (StringUtils.isNotBlank(attendance.getEndDatetime())
                                && DateUtil.compare(attendance.getEndDatetime(),
                                    project.getAttendanceEndtime(),
                                    DateUtil.FRONT_DATE_FORMAT_STRING)) {
                            delay = delay + 1;
                        }
                        // *****************旷工*****************
                    }
                    data.setEarlyDays(early);
                    data.setDelayDays(delay);
                    // 请假天数
                    Double leavingDays = 0.0;
                    if (employ.getDays() != null) {
                        leavingDays = employ.getDays();
                    }
                    data.setLeavingDays(leavingDays);
                    salaryBO.saveSalary(data);
                }

            }
        }

    }

    // 定时器形成考勤记录
    public void createAttendance() {
        // 审核通过，项目开始
        Project condition = new Project();

        Date date = new Date();
        // 获取已经开始的项目
        condition.setStatus(EProjectStatus.Building.getCode());
        List<Project> pList = projectBO.queryProject(condition);
        // 获取各个项目的上下班时间，形成考勤记录
        Attendance data = null;

        String attendanceCode = null;
        for (Project project : pList) {
            // 获取项目下得所有未离职员工
            Employ eCondition = new Employ();
            eCondition.setProjectCode(project.getCode());
            eCondition.setStatus(EStaffStatus.NOT_Leave.getCode());

            List<Employ> eList = employBO.queryEmployList(eCondition);

            String remark = "";
            for (Employ employ : eList) {
                String status = EStaffStatus.Work.getCode();
                // 今天是否请假
                if (isHoilday(employ.getEndDatetime())) {
                    status = EStaffStatus.Hoilday.getCode();
                    remark = "请假";
                }
                employ.setStatus(status);
                employBO.updateStatus(employ);
                data = new Attendance();
                attendanceCode = OrderNoGenerater
                    .generate(EGeneratePrefix.Attendance.getCode());
                data.setCode(attendanceCode);
                data.setProjectCode(project.getCode());
                data.setProjectName(project.getName());

                data.setStaffCode(employ.getStaffCode());
                data.setStaffMobile(employ.getStaffMobile());

                data = new Attendance();
                attendanceCode = OrderNoGenerater
                    .generate(EGeneratePrefix.Attendance.getCode());
                data.setCode(attendanceCode);
                data.setProjectCode(project.getCode());
                data.setProjectName(project.getName());

                data.setStaffCode(employ.getStaffCode());
                data.setStaffMobile(employ.getStaffMobile());
                data.setStatus(EStaffStatus.NOT_Leave.getCode());
                data.setCreateDatetime(date);
                data.setRemark(remark);
                data.setSettleDatetime(
                    DateUtil.getMonth() + "." + project.getSalaryDatetime());
                attendanceBO.saveAttendance(data);
            }

            // --------------------------
            // 统计信息
            // --------------------------

            // 统计每个项目的信息
            Report report = new Report();

            // 上月实发金额
            long lastMonthSalary = getSalary(DateUtil.getMonth() - 1,
                project.getCode());
            report.setLastMonthSalary(lastMonthSalary);
            report.setProjectCode(project.getCode());
            report.setProjectName(project.getName());

            // 目前在职人数
            eCondition.setStatus(EStaffStatus.NOT_Leave.getCode());
            int staffOn = (int) employBO.getTotalCount(eCondition);
            report.setStaffOn(staffOn);

            // 下月预计发放金额
            long nextMonthSalary = employBO.getSalaryCount(eCondition);
            report.setNextMonthSalary(nextMonthSalary);

            // 累积入职人数
            int staffIn = (int) employBO.getTotalCount(eCondition);
            report.setStaffIn(staffIn);

            // 离职人数
            eCondition.setStatus(EStaffStatus.Leave.getCode());
            int staffOut = (int) employBO.getTotalCount(eCondition);
            report.setStaffOut(staffOut);

            // 累积出工人数;当天请假
            eCondition.setStatus(EStaffStatus.Work.getCode());
            int todayWorkers = (int) employBO.getTotalCount(eCondition);
            report.setTodayDays(todayWorkers);

            Report dbData = reportBO.getReportByProject(project.getCode());
            // 新增
            if (dbData == null) {
                reportBO.saveReport(report);
            } else {
                reportBO.refreshReport(dbData.getCode(), report,
                    lastMonthSalary, todayWorkers);
            }

        }

    }

    @Override
    public void stopProject(String code, String updater, String remark) {
        Project data = projectBO.getProject(code);
        if (!EProjectStatus.Building.getCode().equals(data.getStatus())) {
            throw new BizException("xn000", "项目未处于在建状态,无法停工");
        }
        projectBO.stopProject(data, updater, remark);
    }

    @Override
    public void restartProject(String code, String updater, String remark) {
        Project data = projectBO.getProject(code);
        if (!EProjectStatus.Stop.getCode().equals(data.getStatus())) {
            throw new BizException("xn000", "项目未处于停工状态,无需重新开工");
        }
        projectBO.restartProject(data, updater, remark);
    }

    private Long getSalary(int month, String projectCode) {
        Salary condition = new Salary();
        condition.setProjectCode(projectCode);
        condition.setMonth(month);
        long salary = salaryBO.getTotalCount(condition);
        return salary;
    }

    // 今天是否在请假（超过24小时的假期）
    private boolean isHoilday(Date endDatetime) {
        boolean flag = false;
        Date todayStart = DateUtil.getTodayStart();
        if (todayStart.after(endDatetime)) {
            flag = true;
        }
        return flag;
    }

    private String getName(String userId) {
        User user = userBO.getUserName(userId);
        String name = EUser.ADMIN.getCode();
        if (!EUser.ADMIN.getCode().equals(user.getLoginName())) {
            name = user.getRealName();
        }
        return name;

    }
}
