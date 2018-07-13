package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IProjectAO;
import com.cdkj.gchf.bo.ICompanyBO;
import com.cdkj.gchf.bo.ICompanyCardBO;
import com.cdkj.gchf.bo.IDepartmentBO;
import com.cdkj.gchf.bo.IEmployBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IReportBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Page;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.domain.Company;
import com.cdkj.gchf.domain.CompanyCard;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.Report;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631350Req;
import com.cdkj.gchf.dto.req.XN631352Req;
import com.cdkj.gchf.dto.req.XN631353Req;
import com.cdkj.gchf.enums.EBoolean;
import com.cdkj.gchf.enums.EEmployStatus;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EProjectStatus;
import com.cdkj.gchf.enums.EUser;
import com.cdkj.gchf.enums.EUserKind;
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

    @Autowired
    private ICompanyBO companyBO;

    @Override
    public String addProject(XN631350Req req) {
        Project data = new Project();
        Company company = companyBO.getCompany(req.getCompanyCode());

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Project.getCode());
        data.setCode(code);
        data.setCompanyCode(req.getCompanyCode());
        data.setCompanyName(company.getName());
        data.setName(req.getName());
        data.setChargeUser(req.getChargeUser());
        data.setChargeMobile(req.getChargeMobile());

        data.setProvince(req.getProvince());
        data.setCity(req.getCity());
        data.setArea(req.getArea());
        data.setAddress(req.getAddress());
        data.setLongitude(req.getLongitude());

        data.setLatitude(req.getLatitude());
        data.setAttendanceStarttime(req.getAttendanceStarttime());
        data.setAttendanceEndtime(req.getAttendanceEndtime());
        data.setStartDatetime(DateUtil.strToDate(req.getStartDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setSalaryCreateDatetime(req.getSalaryCreateDatetime());

        data.setSalaryDatetime(req.getSalaryDatetime());
        data.setUpdater(req.getUpdater());
        data.setRemark(req.getRemark());
        data.setStatus(EProjectStatus.To_Audit.getCode());
        data.setUpdateDatetime(new Date());
        projectBO.saveProject(data);

        // 添加公司账户
        companyCardBO.saveCompanyCard(data.getCompanyCode(), code,
            req.getBankCode(), req.getBankName(), req.getAccountName(),
            req.getBankcardNumber(), req.getSubbranch(), req.getUpdater(),
            data.getUpdateDatetime(), req.getRemark());

        return code;
    }

    @Override
    @Transactional
    public void editProject(XN631352Req req) {
        Project data = projectBO.getProject(req.getCode());
        data.setName(req.getName());

        data.setChargeUser(req.getChargeUser());
        data.setChargeMobile(req.getChargeMobile());
        if (!(EProjectStatus.To_Audit.getCode().equals(data.getStatus())
                || EProjectStatus.UnPass.getCode().equals(data.getStatus()))) {
            throw new BizException("xn000", "该项目的状态无法修改");
        }
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

        // 更新账户信息
        CompanyCard companyCard = companyCardBO
            .getCompanyCardByProject(req.getCode());
        companyCard.setBankCode(req.getBankCode());
        companyCard.setBankName(req.getBankName());
        companyCard.setBankcardNumber(req.getBankcardNumber());
        companyCard.setSubbranch(req.getSubbranch());

        companyCard.setAccountName(req.getAccountName());
        companyCard.setUpdater(req.getUpdater());
        companyCard.setUpdateDatetime(new Date());
        companyCard.setRemark(req.getRemark());
        companyCardBO.refreshCompanyCard(companyCard);
    }

    @Override
    public void toApprove(XN631353Req req) {
        Project data = projectBO.getProject(req.getCode());
        if (!(EProjectStatus.To_Audit.getCode().equals(data.getStatus())
                || EProjectStatus.UnPass.getCode().equals(data.getStatus()))) {
            throw new BizException("xn0000", "项目不处于待提请审核状态");
        }

        data.setName(req.getName());
        data.setChargeUser(req.getChargeUser());
        data.setChargeMobile(req.getChargeMobile());
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

        data.setStatus(EProjectStatus.UnApprove.getCode());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        projectBO.toApprove(data);

    }

    @Override
    public void approveProject(String code, String result, String approver,
            String approveNote) {
        Project data = projectBO.getProject(code);
        if (!EProjectStatus.UnApprove.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该工程项目未处于待审核状态");
        }
        String status = EProjectStatus.UnPass.getCode();

        if (EBoolean.YES.getCode().equals(result)) {
            status = EProjectStatus.Building.getCode();
            // 添加统计信息
            reportBO.saveReport(data.getCode(), data.getName());
        }

        data.setStatus(status);
        data.setApprover(approver);
        data.setApproveDatetime(new Date());
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
