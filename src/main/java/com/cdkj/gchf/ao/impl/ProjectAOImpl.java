package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IProjectAO;
import com.cdkj.gchf.bo.ICorpBasicinfoBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IProjectBuilderLicenseBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.ISmsOutBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.PhoneUtil;
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

    @Autowired
    private ISmsOutBO smsOutBO;

    @Override
    @Transactional
    public String addProject(XN631600Req req) {

        CorpBasicinfo contractorCorpInfo = corpBasicinfoBO
            .getCorpBasicinfoByCorp(req.getContractorCorpCode());
        if (null == contractorCorpInfo) {
            throw new BizException("XN631600", "总承包单位不存在");
        }

        Project preProject = projectBO.getProjectByFullName(req.getName());
        if (null != preProject) {
            throw new BizException("XN631600", "项目名称已存在，请重新输入");
        }
        String buildCorpName = null;
        if (StringUtils.isNotBlank(req.getBuildCorpCode())) {
            CorpBasicinfo buildCorpInfo = corpBasicinfoBO
                .getCorpBasicinfoByCorp(req.getBuildCorpCode());
            if (null != buildCorpInfo) {
                buildCorpName = buildCorpInfo.getCorpName();
            }
        }

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

        // 添加项目
        String projectCode = projectBO.saveProject(req, contractorCorpInfo,
            buildCorpName);

        // 添加项目管理员
        userBO.saveProjectAdmin(projectCode, req.getName(), req.getLinkMan(),
            req.getLinkPhone());

        // 发送短信
        final XN631600Req tempReq = req;
        new Thread(new Runnable() {

            @Override
            public void run() {
                smsOutBO.sendSmsOut(tempReq.getLinkPhone(),
                    "尊敬的" + PhoneUtil.hideMobile(tempReq.getLinkPhone()) + "，"
                            + tempReq.getName() + "项目管理员账户已开设，登录名："
                            + tempReq.getName().concat("管理员") + "，密码：888888"
                            + "，请登录鲸目项目端查看。",
                    "804080");
            }
        }).start();
        ;

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

        Project project = projectBO.getProject(req.getCode());
        if (!req.getName().equals(project.getName())) {
            Project preProject = projectBO.getProjectByFullName(req.getName());
            if (null != preProject) {
                throw new BizException("XN631600", "项目名称已存在，请重新输入");
            }
        }

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

        CorpBasicinfo buildCorpInfo = null;
        if (StringUtils.isNotBlank(req.getBuildCorpCode())) {
            buildCorpInfo = corpBasicinfoBO
                .getCorpBasicinfoByCorp(req.getBuildCorpCode());
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

        Paginable<Project> page = projectBO.getPaginable(start, limit,
            condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (Project project : page.getList()) {
                ProjectConfig projectConfig = projectConfigBO
                    .getProjectConfigByLocal(project.getCode());
                project.setProjectConfig(projectConfig);
            }
        }

        return page;
    }

    @Override
    public List<Project> queryProjectList(Project condition) {
        return projectBO.queryProjectList(condition);
    }

    @Override
    public Project getProject(String code) {
        Project project = projectBO.getProject(code);

        List<ProjectBuilderLicense> projectBuilderLicenseList = projectBuilderLicenseBO
            .queryProjectBuilderLicenseList(code);
        project.setBuilderLicenses(projectBuilderLicenseList);

        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByLocal(project.getCode());
        project.setProjectConfig(projectConfig);

        return project;
    }

}
