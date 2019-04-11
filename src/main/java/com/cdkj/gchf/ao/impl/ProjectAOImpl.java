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
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.ProjectBuilderLicense;
import com.cdkj.gchf.dto.req.XN631600Req;
import com.cdkj.gchf.dto.req.XN631602Req;
import com.cdkj.gchf.dto.res.XN631616Res;
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

        CorpBasicinfo contractorCorpInfo = corpBasicinfoBO
            .getCorpBasicinfoByCorp(req.getContractorCorpCode());
        if (null == contractorCorpInfo) {
            throw new BizException("XN631600", "总承包单位不存在");
        }

        CorpBasicinfo buildCorpInfo = null;
        if (StringUtils.isNotBlank(req.getBuildCorpCode())) {
            buildCorpInfo = corpBasicinfoBO
                .getCorpBasicinfoByCorp(req.getBuildCorpCode());
            if (null == buildCorpInfo) {
                throw new BizException("XN631600", "建设单位不存在");
            }
        }
        String projectCode = projectBO.saveProject(req, contractorCorpInfo,
            buildCorpInfo);
        userBO.saveProjectAdmin(projectCode, req.getName());
        // 添加施工许可证
        projectBuilderLicenseBO.saveProjectBuilderLicense(projectCode,
            req.getBuilderLicenses());

        return projectCode;
    }

    @Override
    @Transactional
    public void editProject(XN631602Req req) {

        CorpBasicinfo contractorCorpInfo = corpBasicinfoBO
            .getCorpBasicinfoByCorp(req.getContractorCorpCode());
        if (null == contractorCorpInfo) {
            throw new BizException("XN631600", "总承包单位不存在");
        }

        CorpBasicinfo buildCorpInfo = null;
        if (StringUtils.isNotBlank(req.getBuildCorpCode())) {
            buildCorpInfo = corpBasicinfoBO
                .getCorpBasicinfoByCorp(req.getBuildCorpCode());
            if (null == buildCorpInfo) {
                throw new BizException("XN631600", "建设单位不存在");
            }
        }

        // 删除旧的施工许可证
        projectBuilderLicenseBO.removeByProject(req.getCode());

        // 添加施工许可证
        projectBuilderLicenseBO.saveProjectBuilderLicense(req.getCode(),
            req.getBuilderLicenses());

        projectBO.refreshProject(req, contractorCorpInfo, buildCorpInfo);
    }

    @Override
    public Paginable<Project> queryProjectPage(int start, int limit,
            Project condition) {
        return projectBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Project> queryProjectList(Project condition) {
        return projectBO.queryProjectList(condition);
    }

    @Override
    public Project getProject(String code) {
        Project project = projectBO.getProject(code);
        return project;
    }

    @Override
    public Object getProjectAndLicense(String code) {
        Project project = projectBO.getProject(code);
        ProjectBuilderLicense condition = new ProjectBuilderLicense();
        condition.setProjectCode(project.getCode());
        List<ProjectBuilderLicense> queryProjectBuilderLicenseList = projectBuilderLicenseBO
            .queryProjectBuilderLicenseList(condition);
        XN631616Res xn631616Res = new XN631616Res();
        xn631616Res.setProject(project);
        xn631616Res.setLicense(queryProjectBuilderLicenseList);
        return xn631616Res;
    }

}
