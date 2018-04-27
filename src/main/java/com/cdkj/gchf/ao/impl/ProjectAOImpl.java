package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IProjectAO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.dto.req.XN631350Req;
import com.cdkj.gchf.dto.req.XN631352Req;
import com.cdkj.gchf.enums.EBoolean;
import com.cdkj.gchf.enums.EProjectStatus;

@Service
public class ProjectAOImpl implements IProjectAO {

    @Autowired
    private IProjectBO projectBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public String addProject(XN631350Req req) {
        Project project = new Project();
        project.setName(req.getName());
        project.setChargeUser(req.getChargeUser());
        project
            .setChargeMobile(userBO.getUser(req.getChargeUser()).getMobile());
        project.setBankCrandNumber(req.getBankCardNumber());
        project.setBankName(req.getBankName());
        project.setSubbranch(req.getSubbranch());
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
        return projectBO.saveProject(project);
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
