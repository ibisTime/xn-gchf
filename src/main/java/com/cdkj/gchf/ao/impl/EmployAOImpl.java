package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IEmployAO;
import com.cdkj.gchf.bo.IAttendanceBO;
import com.cdkj.gchf.bo.IBankCardBO;
import com.cdkj.gchf.bo.ICcontractBO;
import com.cdkj.gchf.bo.ICompanyBO;
import com.cdkj.gchf.bo.IDepartmentBO;
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
import com.cdkj.gchf.domain.BankCard;
import com.cdkj.gchf.domain.Company;
import com.cdkj.gchf.domain.Department;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.Report;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631460Req;
import com.cdkj.gchf.dto.req.XN631462Req;
import com.cdkj.gchf.dto.req.XN631463Req;
import com.cdkj.gchf.enums.EAttendanceStatus;
import com.cdkj.gchf.enums.EEmployStatus;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EProjectStatus;
import com.cdkj.gchf.enums.EStaffSalaryStatus;
import com.cdkj.gchf.enums.EUser;
import com.cdkj.gchf.exception.BizException;

@Service
public class EmployAOImpl implements IEmployAO {
    private static final Logger logger = LoggerFactory
        .getLogger(AttendanceAOImpl.class);

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

    @Autowired
    private IDepartmentBO departmentBO;

    @Autowired
    private ICompanyBO companyBO;

    @Autowired
    private IBankCardBO bankCardBO;

    @Override
    @Transactional
    public String joinIn(XN631460Req req) {
        Project project = projectBO.getProject(req.getProjectCode());
        if (!EProjectStatus.Building.getCode().equals(project.getStatus())) {
            throw new BizException("xn0000", "该项目未通过审核或已停工");
        }

        Employ checkData = employBO.getEmployByStaff(req.getStaffCode(),
            req.getProjectCode());
        // 防止重复办理入职
        if (checkData != null && !EEmployStatus.Leave.getCode()
            .equals(checkData.getStatus())) {
            throw new BizException("xn0000", "该员工已入职该项目，请勿重复办理入职");
        }

        Employ data = new Employ();
        Date date = new Date();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Employ.getCode());
        Staff staff = staffBO.getStaff(req.getStaffCode());

        data.setCode(code);
        data.setCompanyCode(req.getCompanyCode());
        data.setProjectCode(req.getProjectCode());
        data.setProjectName(project.getName());
        data.setDepartmentCode(req.getDepartmentCode());
        data.setStaffCode(staff.getCode());
        data.setStaffName(staff.getName());
        data.setStaffMobile(staff.getMobile());

        data.setType(req.getType());
        data.setPosition(req.getPosition());
        data.setUpUser(req.getDepartmentCode());
        data.setSalary(StringValidater.toLong(req.getSalary()));
        data.setCutAmount(StringValidater.toLong(req.getCutAmount()));

        data.setStatus(EEmployStatus.Work.getCode());
        data.setJoinDatetime(DateUtil.strToDate(req.getJoinDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setSalaryStatus(EStaffSalaryStatus.Normal.getCode());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(date);
        data.setRemark(req.getRemark());
        employBO.joinIn(data);

        // 计入累积入职
        Long nextMonthSalary = AmountUtil.mul(data.getSalary(),
            DateUtil.getRemainDays());
        reportBO.refreshStaffIn(project.getCode(), nextMonthSalary);

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

        // 添加银行卡信息
        bankCardBO.addBankCard(staff, req.getCompanyCode(),
            req.getProjectCode(), req.getBankCode(), req.getBankName(),
            req.getSubbranch(), req.getBankcardNumber(), req.getUpdater());

        // 记录员工日志
        staffLogBO.saveStaffLog(data, staff.getName(), project.getCompanyCode(),
            project.getCode(), project.getName());
        return code;
    }

    @Override
    @Transactional
    public void editEmploy(XN631463Req req) {
        Project project = projectBO.getProject(req.getProjectCode());
        if (!EProjectStatus.Building.getCode().equals(project.getStatus())) {
            throw new BizException("xn0000", "该项目未通过审核或已停工");
        }

        Employ employ = employBO.getEmploy(req.getCode());

        // 修改入职
        employ.setProjectCode(req.getProjectCode());
        employ.setProjectName(project.getName());
        employ.setDepartmentCode(req.getDepartmentCode());
        employ.setType(req.getType());
        employ.setPosition(req.getPosition());

        employ.setSalary(StringValidater.toLong(req.getSalary()));
        employ.setJoinDatetime(DateUtil.strToDate(req.getJoinDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        employ.setCutAmount(StringValidater.toLong(req.getCutAmount()));
        employ.setUpdater(req.getUpdater());
        employ.setUpdateDatetime(new Date());

        employ.setRemark(req.getRemark());
        employBO.editEmploy(employ);

        // 如果没有工资卡则新增，否则修改
        Staff staff = staffBO.getStaff(employ.getStaffCode());
        BankCard bankCard = bankCardBO.getBankCard(employ.getStaffCode(),
            employ.getProjectCode());
        if (null != bankCard) {
            bankCardBO.refreshBankCard(bankCard.getCode(), req.getBankCode(),
                req.getBankName(), req.getSubbranch(), req.getBankcardNumber(),
                req.getUpdater(), req.getRemark());
        } else {
            bankCardBO.addBankCard(staff, employ.getCompanyCode(),
                req.getProjectCode(), req.getBankCode(), req.getBankName(),
                req.getSubbranch(), req.getBankcardNumber(), req.getUpdater());
        }

        // 记录员工日志
        staffLogBO.saveStaffLog(employ, employ.getStaffName(),
            project.getCompanyCode(), project.getCode(), project.getName());
    }

    @Override
    @Transactional
    public void leaveOffice(XN631462Req req) {
        Employ data = employBO.getEmployByStaff(req.getStaffCode(),
            req.getProjectCode());
        if (data == null) {
            throw new BizException("xn0000", "该员工未在该项目任职");
        }
        if (EEmployStatus.Leave.getCode().equals(data.getStatus())) {
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
                - AmountUtil.mul(data.getSalary(), DateUtil.getRemainDays()));
        reportBO.refreshNextMonthSalary(report);

        Project project = projectBO.getProject(data.getProjectCode());
        staffLogBO.saveStaffLog(data, data.getStaffCode(),
            project.getCompanyCode(), project.getCode(), project.getName());

    }

    @Override
    public Paginable<Employ> queryEmployPage(int start, int limit,
            Employ condition) {
        Paginable<Employ> page = employBO.getPaginable(start, limit, condition);
        List<Employ> list = page.getList();
        for (Employ employ : list) {
            // 员工信息
            Staff staff = staffBO.getStaffBrief(employ.getStaffCode());
            employ.setStaff(staff);

            initEmploy(employ);
        }
        page.setList(list);
        return page;
    }

    @Override
    public List<Employ> queryEmployList(Employ condition) {
        // 补充信息
        List<Employ> list = employBO.queryEmployList(condition);
        for (Employ employ : list) {
            // 员工信息
            Staff staff = staffBO.getStaffBrief(employ.getStaffCode());
            employ.setStaff(staff);

            initEmploy(employ);
        }
        return list;
    }

    @Override
    public Employ getEmploy(String code) {
        Employ data = employBO.getEmploy(code);
        // 员工信息
        Staff staff = staffBO.getStaff(data.getStaffCode());
        data.setStaff(staff);

        initEmploy(data);
        return data;
    }

    private void initEmploy(Employ employ) {
        // 公司名称
        Company company = companyBO.getCompany(employ.getCompanyCode());
        employ.setCompanyName(company.getName());

        // 部门名称
        Department department = departmentBO
            .getDepartment(employ.getDepartmentCode());
        employ.setDepartmentName(department.getName());

        // 上级人员
        employ.setUpUserName(getName(employ.getUpUser()));

        // 更新人
        employ.setUpdateName(getName(employ.getUpdater()));
    }

    private String getName(String userId) {
        User user = userBO.getUserName(userId);
        String name = EUser.ADMIN.getCode();
        if (null != user
                && !EUser.ADMIN.getCode().equals(user.getLoginName())) {
            name = user.getRealName();
        }
        return name;
    }

    // 每天凌晨将请假到期的员工更新为在职
    @Override
    public void updateEmployStatusDaily() {
        Employ eCondition = new Employ();
        eCondition.setStatus(EEmployStatus.Not_Leave.getCode());
        List<Employ> eList = employBO.queryEmployList(eCondition);
        logger.info("===========开始更新员工状态==============");

        for (Employ employ : eList) {
            String status = EEmployStatus.Work.getCode();
            if (employ.getStartDatetime() != null
                    && employ.getLastLeavingDays() != null) {
                // 如果今天请假则跳出循环，请假开始时间也算为1天
                if (DateUtil.isIn(employ.getStartDatetime(),
                    DateUtil.getRelativeDateOfDays(employ.getStartDatetime(),
                        employ.getLastLeavingDays()))) {
                    break;
                }
            }
            employ.setStatus(status);
            employBO.updateStatus(employ);
        }
        logger.info("===========结束更新员工状态==============");
    }
}
