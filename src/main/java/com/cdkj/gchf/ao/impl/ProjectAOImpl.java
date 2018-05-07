package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IProjectAO;
import com.cdkj.gchf.bo.ICompanyBO;
import com.cdkj.gchf.bo.ICompanyCardBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IReportBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.domain.Company;
import com.cdkj.gchf.domain.CompanyCard;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.Report;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631350Req;
import com.cdkj.gchf.dto.req.XN631352Req;
import com.cdkj.gchf.dto.req.XN631353Req;
import com.cdkj.gchf.enums.EBoolean;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EProjectStatus;
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
    private IReportBO reportBO;

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

        data.setStatus(EProjectStatus.To_Audit.getCode());
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
        if (!(EProjectStatus.To_Audit.getCode().equals(data.getStatus())
                || EProjectStatus.UnPass.getCode().equals(data.getStatus()))) {
            throw new BizException("xn000", "该项目的状态无法修改");
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
        if (!EProjectStatus.To_Audit.getCode().equals(data.getStatus())) {
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

        data.setStatus(EProjectStatus.To_Audit.getCode());
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
        String status = EProjectStatus.Building.getCode();
        if (EBoolean.NO.getCode().equals(result)) {
            status = EProjectStatus.UnPass.getCode();
        }

        data.setStatus(status);
        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        if (StringUtils.isBlank(approveNote)) {
            approveNote = "您申请的项目[" + data.getName() + "]未通过审核,请在修改后再次申请";
        }
        data.setApproveNote(approveNote);
        projectBO.approveProject(data);
        // 添加统计信息
        if (EProjectStatus.Building.getCode().equals(status)) {
            reportBO.saveReport(data.getCode(), data.getName());
        }
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
