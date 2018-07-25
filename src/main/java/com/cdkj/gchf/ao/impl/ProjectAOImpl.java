package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IProjectAO;
import com.cdkj.gchf.bo.ICompanyCardBO;
import com.cdkj.gchf.bo.IDepartmentBO;
import com.cdkj.gchf.bo.IEmployBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IReportBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Page;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.common.MD5Util;
import com.cdkj.gchf.common.PwdUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.domain.CompanyCard;
import com.cdkj.gchf.domain.Department;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.Report;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631350Req;
import com.cdkj.gchf.dto.req.XN631352Req;
import com.cdkj.gchf.enums.EEmployStatus;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EProjectStatus;
import com.cdkj.gchf.enums.EUser;
import com.cdkj.gchf.enums.EUserKind;
import com.cdkj.gchf.enums.EUserStatus;
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
    private IReportBO reportBO;

    @Autowired
    IEmployBO employBO;

    @Autowired
    IDepartmentBO departmentBO;

    // 项目管理员默认密码
    private final String userLoginPwd = "888888";

    // 项目管理员默认角色：业主端管理员
    private final String userRole = "RO201800000000000003";

    @Override
    @Transactional
    public String addProject(XN631350Req req) {
        Project data = new Project();

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Project.getCode());
        data.setCode(code);
        data.setName(req.getName());
        data.setUpdater(req.getUpdater());
        data.setRemark(req.getRemark());
        data.setStatus(EProjectStatus.To_Edit.getCode());
        data.setUpdateDatetime(new Date());
        projectBO.saveProject(data);

        // 添加项目管理员
        User user = new User();
        String userId = OrderNoGenerater.generate("U");
        user.setUserId(userId);
        user.setType(EUserKind.Owner.getCode());
        user.setRealName(req.getName().concat("管理员"));
        user.setLoginName(req.getName().concat("管理员"));
        user.setProjectCode(code);
        user.setProjectName(req.getName());

        user.setLoginPwd(MD5Util.md5(userLoginPwd));
        user.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(userLoginPwd));
        user.setCreateDatetime(new Date());
        user.setRoleCode(userRole);
        user.setStatus(EUserStatus.NORMAL.getCode());
        userBO.saveUser(user);

        return code;
    }

    @Override
    @Transactional
    public void editProject(XN631352Req req) {
        Project data = projectBO.getProject(req.getCode());
        if (EProjectStatus.End.getStatus().equals(data.getStatus())) {
            throw new BizException("xn000", "该项目已结束，无法编辑");
        }

        data.setName(req.getName());
        data.setChargeUser(req.getChargeUser());
        data.setChargeMobile(req.getChargeMobile());

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

        String status = data.getStatus();
        if (EProjectStatus.To_Edit.getCode().equals(status)) {
            status = EProjectStatus.To_Building.getCode();
        }
        data.setStatus(status);
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        projectBO.editProject(data);

        // 更新账户信息
        // 添加公司账户
        CompanyCard companyCard = companyCardBO
            .getCompanyCardByProject(req.getCode());
        if (null != companyCard) {
            companyCardBO.refreshCompanyCard(req.getCode(), req.getBankCode(),
                req.getBankName(), req.getAccountName(),
                req.getBankcardNumber(), req.getSubbranch(), req.getUpdater(),
                data.getUpdateDatetime(), req.getRemark());
        } else {
            companyCardBO.saveCompanyCard(req.getCode(), req.getBankCode(),
                req.getBankName(), req.getAccountName(),
                req.getBankcardNumber(), req.getSubbranch(), req.getUpdater(),
                data.getUpdateDatetime(), req.getRemark());
        }
    }

    @Override
    public void startProject(String code, String approver, String approveNote) {
        Project data = projectBO.getProject(code);

        if (!EProjectStatus.To_Building.getCode().equals(data.getStatus())) {
            throw new BizException("xn000", "该项目不处于待开工状态！");
        }

        Department departmentCondition = new Department();
        departmentCondition.setProjectCode(code);
        List<Department> departmentsList = departmentBO
            .queryDepartmentList(departmentCondition);
        if (CollectionUtils.isEmpty(departmentsList)) {
            throw new BizException("xn000", "请编辑施工单位后再开工！");
        }

        // 添加统计信息
        Report report = reportBO.getReportByProject(code);
        if (null == report) {
            reportBO.saveReport(data.getCode(), data.getName());
        }

        data.setStatus(EProjectStatus.Building.getCode());
        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setApproveNote(approveNote);
        projectBO.startProject(data);
    }

    @Override
    public void pauseProject(String code, String updater, String remark) {
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

    @Override
    public void endProject(String code, String endDatetime, String updater,
            String remark) {
        Project data = projectBO.getProject(code);
        if (!EProjectStatus.Building.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该工程项目未处于在建状态");
        }
        projectBO.projectEnd(data, endDatetime, updater, remark);
        // 项目结束，员工离职
        Employ condition = new Employ();
        condition.setProjectCode(data.getCode());
        condition.setStatus(EEmployStatus.Not_Leave.getCode());
        List<Employ> list = employBO.queryEmployList(condition);

        for (Employ employ : list) {
            employ.setStatus(EEmployStatus.Leave.getCode());
            employ.setLeavingDatetime(DateUtil.strToDate(endDatetime,
                DateUtil.FRONT_DATE_FORMAT_STRING));
            employBO.updateStatus(employ);
        }
    }

    @Override
    public void editSalaryDelayDays(String code, Integer salaryDelayDays) {
        Project data = projectBO.getProject(code);
        if (!EProjectStatus.Building.getCode().equals(data.getStatus())) {
            throw new BizException("xn000", "项目未处于在建状态,无法设置薪资发放可延迟天数");
        }
        data.setSalaryDelayDays(salaryDelayDays);
        projectBO.editSalaryDelayDays(data);
    }

    @Override
    public Paginable<Project> queryProjectPage(int start, int limit,
            Project condition) {
        Paginable<Project> page = new Page<Project>();
        List<Project> list = new ArrayList<Project>();
        if (EUserKind.Supervise.getCode().equals(condition.getKind())) {
            if (CollectionUtils.isEmpty(condition.getProjectCodeList())) {
                page.setList(list);
                return page;
            }
        }

        page = projectBO.getPaginable(start, limit, condition);
        for (Project project : page.getList()) {
            project.setApproveName(getName(project.getApprover()));
            project.setUpdateName(getName(project.getUpdater()));
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
        if (report != null) {
            data.setReport(report);
        }

        // 补全名字信息
        String approveName = getName(data.getApprover());
        String updateName = getName(data.getUpdater());
        data.setApproveName(approveName);
        data.setUpdateName(updateName);
        return data;
    }

    @Override
    public List<Project> queryProjectList(Project condition) {
        List<Project> list = new ArrayList<Project>();
        if (EUserKind.Supervise.getCode().equals(condition.getKind())) {
            if (CollectionUtils.isEmpty(condition.getProjectCodeList())) {
                return list;
            }
        }
        list = projectBO.queryProject(condition);

        for (Project project : list) {
            project.setApproveName(getName(project.getApprover()));
            project.setUpdateName(getName(project.getUpdater()));
        }
        return list;
    }

    private String getName(String userId) {
        User user = userBO.getUserName(userId);
        String name = null;
        if (user != null) {
            name = EUser.ADMIN.getCode();
            if (!EUser.ADMIN.getCode().equals(user.getLoginName())) {
                name = user.getRealName();
            }
        }
        return name;
    }
}
