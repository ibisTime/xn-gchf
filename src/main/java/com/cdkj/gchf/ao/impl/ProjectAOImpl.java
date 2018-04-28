package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IDepartmentAO;
import com.cdkj.gchf.ao.IProjectAO;
import com.cdkj.gchf.bo.ICompanyBO;
import com.cdkj.gchf.bo.ICompanyCardBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.domain.Company;
import com.cdkj.gchf.domain.Department;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631350Req;
import com.cdkj.gchf.dto.req.XN631352Req;
import com.cdkj.gchf.enums.EBoolean;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EProjectStatus;
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

    @Override
    public String addProject(XN631350Req req) {
        Project project = new Project();

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Project.getCode());
        project.setCode(code);
        project.setName(req.getName());
        project.setChargeUser(req.getChargeUser());

        User user = userBO.getUser(req.getChargeUser());
        if (StringUtils.isNotBlank(user.getMobile())) {
            project.setChargeMobile(user.getMobile());
        }

        project.setStartDatetime(DateUtil.strToDate(req.getStartDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        project.setLongitude(req.getLongitude());
        project.setLatitude(req.getLatitude());
        project.setProvince(req.getProvince());
        project.setCity(req.getCity());

        project.setArea(req.getArea());
        project.setAddress(req.getAddress());
        project.setSalaryDatetime(DateUtil.strToDate(req.getSalaryDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        project.setStatus(EProjectStatus.TO_AUDIT.getCode());
        project.setUpdater(req.getUpdater());

        project.setUpdateDatetime(new Date());
        project.setRemark(req.getRemark());

        // 添加公司账户
        Department dpData = departmentAO
            .getDepartment(user.getDepartmentCode());
        if (dpData == null) {
            throw new BizException("xn0000", "该负责人 不属于任何部门");
        }
        Company company = companyBO.getCompany(dpData.getCompanyCode());
        if (company == null) {
            throw new BizException("xn0000", "该部门 不属于任何公司");
        }
        companyCardBO.saveCompanyCard(code, req.getName(), company.getCode(),
            company.getName(), req.getBankCode(), req.getBankName(),
            req.getBankCardNumber(), req.getSubbranch());
        return code;
    }

    @Override
    public void editProject(XN631352Req req) {
        Project project = projectBO.getProject(req.getCode());
        project.setName(req.getName());
        project.setChargeUser(req.getChargeUser());
        project.setChargeMobile(req.getChargeMobile());
        project.setEndDatetime(DateUtil.strToDate(req.getEndDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        project.setLongitude(req.getLongitude());
        project.setLatitude(req.getLatitude());
        project.setProvince(req.getProvince());
        project.setCity(req.getCity());
        project.setArea(req.getArea());
        project.setAddress(req.getAddress());
        project.setSalaryDatetime(DateUtil.strToDate(req.getSalaryDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        project.setUpdater(req.getUpdater());
        project.setUpdateDatetime(new Date());
        projectBO.editProject(project);
    }

    @Override
    public Paginable<Project> queryProjectPage(int start, int limit,
            Project condition) {
        return projectBO.getPaginable(start, limit, condition);
    }

    @Override
    public Project getProject(String code) {
        return projectBO.getProject(code);
    }

    @Override
    public List<Project> queryProjectList(Project condition) {
        return projectBO.queryProject(condition);
    }

    @Override
    public void auditProject(String code, String result, String auditor,
            String remark) {
        Project data = projectBO.getProject(code);
        if (!EProjectStatus.TO_AUDIT.getCode().equals(data.getStatus())) {
            throw new com.cdkj.gchf.exception.BizException("mag",
                "该项目已被审核，请重新选择");
        }
        String status = EProjectStatus.PASS.getCode();
        if (EBoolean.NO.getCode().equals(result)) {
            status = EProjectStatus.PASS.getCode();
        }

        data.setStatus(status);
        data.setAuditor(auditor);
        data.setAuditDatetime(new Date());
        data.setRemark(remark);
        projectBO.auditProject(data);
    }

}
