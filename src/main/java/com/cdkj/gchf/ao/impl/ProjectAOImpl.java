package com.cdkj.gchf.ao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IDepartmentAO;
import com.cdkj.gchf.ao.IProjectAO;
import com.cdkj.gchf.bo.IAttendanceBO;
import com.cdkj.gchf.bo.ICompanyBO;
import com.cdkj.gchf.bo.ICompanyCardBO;
import com.cdkj.gchf.bo.IEmployBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.Attendance;
import com.cdkj.gchf.domain.Company;
import com.cdkj.gchf.domain.CompanyCard;
import com.cdkj.gchf.domain.Department;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.domain.Project;
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
    private IDepartmentAO departmentAO;

    @Autowired
    private IAttendanceBO attendanceBO;

    @Autowired
    private IEmployBO employBO;

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
        Department dpData = departmentAO
            .getDepartment(user.getDepartmentCode());
        if (dpData == null) {
            throw new BizException("xn0000", "该负责人 不属于任何部门");
        }

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
        return projectBO.getPaginable(start, limit, condition);
    }

    @Override
    public Project getProject(String code) {
        Project data = projectBO.getProject(code);
        CompanyCard companyCard = companyCardBO
            .getCompanyCardByProject(data.getCode());
        data.setCompanyCard(companyCard);
        return data;
    }

    @Override
    public List<Project> queryProjectList(Project condition) {
        return projectBO.queryProject(condition);
    }

    @Override
    public void toApprove(XN631353Req req) {
        Project data = projectBO.getProject(req.getCode());
        if (!EProjectStatus.TO_AUDIT_NO.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "项目已经进入审核,无法修改");
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
        if (!EProjectStatus.PASS.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该工程项目还未通过审核");
        }
        if (DateUtil.strToDate(endDatetime, DateUtil.FRONT_DATE_FORMAT_STRING)
            .before(new Date())) {
            throw new BizException("xn0000", "请输入正确的结束时间");
        }

        projectBO.projectEnd(data, endDatetime, updater, remark);
    }

    // 定时器形成工资条
    public void createSalary() {
        Date date = new Date();
        // 获取已经开始的项目
        Project condition = new Project();
        condition.setStatus(EProjectStatus.PASS.getCode());
        List<Project> list = projectBO.queryProject(condition);
        // 获取各个项目的上下班时间，形成考勤记录
        for (Project project : list) {
            // 若当前时间是工资条生成时间,形成工资条
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            if (day == StringValidater
                .toInteger(project.getSalaryCreateDatetime())) {
                // 生成工资条
                Employ eCondition = new Employ();
                eCondition.setProjectCode(project.getCode());
                List<Employ> eList = employBO.queryEmployList(eCondition);
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

                    // 统计上个月工人的请假、迟到、早退天数
                    Date startDatetime = DateUtil
                        .getFristDay(calendar.get(Calendar.MONTH) - 1);
                    Date endDatetime = DateUtil
                        .getLastDay(calendar.get(Calendar.MONTH) - 1);
                    Attendance aCondition = new Attendance();
                    aCondition.setCreateDatetimeStart(startDatetime);
                    aCondition.setCreateDatetimeEnd(endDatetime);
                    aCondition
                        .setStatus(EAttendanceStatus.Not_Normal.getCode());
                    List<Attendance> aList = attendanceBO
                        .queryAttendanceList(aCondition);

                    // 计算上月迟到和早退天数
                    int early = 0;
                    int delay = 0;
                    for (Attendance attendance : aList) {
                        if (EAttendanceStatus.EarLy.getCode()
                            .equals(attendance.getStatus())) {
                            early = early + 1;
                        } else if (EAttendanceStatus.Later.getCode()
                            .equals(attendance.getStatus())) {
                            delay = delay + 1;
                        } else {
                            early = early + 1;
                            delay = delay + 1;
                        }
                    }
                    data.setEarlyDays(early);
                    data.setDelayDays(delay);
                    // ***********请假天数****************

                }
            }
        }

    }

    // 定时器形成考勤记录
    public void createAttendance() {
        Date date = new Date();
        // 获取已经开始的项目
        Project condition = new Project();
        condition.setStatus(EProjectStatus.PASS.getCode());
        List<Project> list = projectBO.queryProject(condition);
        // 获取各个项目的上下班时间，形成考勤记录
        Attendance data = null;
        for (Project project : list) {
            // 获取项目下得所有未离职员工
            Employ eCondition = new Employ();
            eCondition.setProjectCode(project.getCode());
            eCondition.setStatus(EStaffStatus.NOT_Leave.getCode());
            List<Employ> eList = employBO.queryEmployList(eCondition);
            for (Employ employ : eList) {
                data = new Attendance();
                String code = OrderNoGenerater
                    .generate(EGeneratePrefix.Attendance.getCode());
                data.setCode(code);
                data.setProjectCode(project.getCode());
                data.setProjectName(project.getName());
                data.setStaffCode(employ.getStaffCode());
                data.setStaffMobile(employ.getStaffMobile());

                data.setCreateDatetime(date);
                data.setSettleDatetime(project.getSalaryDatetime());
                attendanceBO.saveAttendance(data);
            }

        }

    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.MONTH);
        System.out.println(day);
    }

}
