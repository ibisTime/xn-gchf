package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IProjectAO;
import com.cdkj.gchf.bo.ICorpBasicinfoBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IProjectBuilderLicenseBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.StringUtil;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.ProjectBuilderLicense;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.dto.req.XN631600Req;
import com.cdkj.gchf.dto.req.XN631602Req;
import com.cdkj.gchf.exception.BizException;

@Service
public class ProjectAOImpl implements IProjectAO {

    @Autowired
    private IProjectBO projectBO;

    @Autowired
    private IProjectBuilderLicenseBO projectBuilderLicenseBO;

    @Autowired
    private ICorpBasicinfoBO corpBasicinfoBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IProjectConfigBO projectConfigBO;

    @Override
    @Transactional
    public String addProject(XN631600Req req) {

        if (StringUtils.isNotBlank(req.getBuildingArea())
                && !StringUtil.isNumber(req.getBuildingArea())) {
            throw new BizException("XN631600", "【总面积】为数字类型，请重新填写");
        }

        if (StringUtils.isNotBlank(req.getBuildingLength())
                && !StringUtil.isNumber(req.getBuildingLength())) {
            throw new BizException("XN631600", "【总长度】为数字类型，请重新填写");
        }

        if (StringUtils.isNotBlank(req.getInvest())
                && !StringUtil.isNumber(req.getInvest())) {
            throw new BizException("XN631600", "【总投资】为数字类型，请重新填写");
        }
        if (StringUtils.isNotBlank(req.getLat())
                && !StringUtil.isNumber(req.getLat())) {
            throw new BizException("XN631600", "【经度】为数字类型，请重新填写");
        }

        if (StringUtils.isNotBlank(req.getLng())
                && !StringUtil.isNumber(req.getLng())) {
            throw new BizException("XN631600", "【纬度】为数字类型，请重新填写");
        }

        Project preProject = projectBO.getProjectByFullName(req.getName());
        if (null != preProject) {
            throw new BizException("XN631600", "项目名称已存在，请重新输入");
        }

        CorpBasicinfo contractorCorpInfo = corpBasicinfoBO
            .getCorpBasicinfoByCorp(req.getContractorCorpCode());
        if (null == contractorCorpInfo) {
            throw new BizException("XN631600", "总承包单位不存在");
        }

        // 添加项目
        String projectCode = projectBO.saveProject(req, contractorCorpInfo);

        // 添加项目管理员
        userBO.saveProjectAdmin(projectCode, req);

        // 添加施工许可证
        projectBuilderLicenseBO.saveProjectBuilderLicense(projectCode,
            req.getBuilderLicenses());

        return projectCode;
    }

    @Override
    @Transactional
    public void editProject(XN631602Req req) {

        if (StringUtils.isNotBlank(req.getBuildingArea())
                && !StringUtil.isNumber(req.getBuildingArea())) {
            throw new BizException("XN631600", "【总面积】为数字类型，请重新填写");
        }

        if (StringUtils.isNotBlank(req.getBuildingLength())
                && !StringUtil.isNumber(req.getBuildingLength())) {
            throw new BizException("XN631600", "【总长度】为数字类型，请重新填写");
        }

        if (StringUtils.isNotBlank(req.getInvest())
                && !StringUtil.isNumber(req.getInvest())) {
            throw new BizException("XN631600", "【总投资】为数字类型，请重新填写");
        }

        if (StringUtils.isNotBlank(req.getLat())
                && !StringUtil.isNumber(req.getLat())) {
            throw new BizException("XN631600", "【经度】为数字类型，请重新填写");
        }

        if (StringUtils.isNotBlank(req.getLng())
                && !StringUtil.isNumber(req.getLng())) {
            throw new BizException("XN631600", "【纬度】为数字类型，请重新填写");
        }

        CorpBasicinfo contractorCorpInfo = corpBasicinfoBO
            .getCorpBasicinfoByCorp(req.getContractorCorpCode());
        if (null == contractorCorpInfo) {
            throw new BizException("XN631600", "总承包单位不存在");
        }

        Project project = projectBO.getProject(req.getCode());
        if (!req.getName().equals(project.getName())) {
            Project preProject = projectBO.getProjectByFullName(req.getName());
            if (null != preProject) {
                throw new BizException("XN631600", "项目名称已存在，请重新输入");
            }
        }

        // 删除旧的施工许可证
        projectBuilderLicenseBO.removeByProject(req.getCode());

        // 添加施工许可证
        projectBuilderLicenseBO.saveProjectBuilderLicense(req.getCode(),
            req.getBuilderLicenses());

        projectBO.refreshProject(req, contractorCorpInfo);
    }

    @Override
    public Paginable<Project> queryProjectPage(int start, int limit,
            Project condition) {

        Paginable<Project> page = projectBO.getPaginable(start, limit,
            condition);

        return page;
    }

    @Override
    public List<Project> queryProjectList(Project condition) {
        return projectBO.queryProjectList(condition);
    }

    @Override
    public Project getProject(String code) {
        Project project = projectBO.getProject(code);
        // 查询项目施工许可证
        List<ProjectBuilderLicense> projectBuilderLicenseList = projectBuilderLicenseBO
            .queryProjectBuilderLicenseList(code);
        project.setBuilderLicenses(projectBuilderLicenseList);
        // 项目配置
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByLocal(project.getCode());
        project.setProjectConfig(projectConfig);

        return project;
    }

}
