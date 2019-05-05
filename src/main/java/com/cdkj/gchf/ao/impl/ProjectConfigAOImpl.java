package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IProjectConfigAO;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631620Req;
import com.cdkj.gchf.dto.req.XN631622Req;
import com.cdkj.gchf.enums.EOperateLogOperate;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EProjectConfigStatus;
import com.cdkj.gchf.enums.ESecretStatus;
import com.cdkj.gchf.exception.BizException;

@Service
public class ProjectConfigAOImpl implements IProjectConfigAO {

    @Autowired
    private IProjectConfigBO projectConfigBO;

    @Autowired
    private IOperateLogBO operateLogBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IProjectBO projectBO;

    @Override
    @Transactional
    public String addProjectConfig(XN631620Req req) {

        ProjectConfig configByProject = projectConfigBO
            .getProjectConfigByLocal(req.getProjectCode());
        if (null != configByProject) {
            throw new BizException("XN631620", "该项目已添加配置,无法再次添加");
        }

        projectConfigBO.checkProjectConfigBySecret(req.getProjectCode(),
            req.getSecret());

        Project project = projectBO.getProject(req.getLocalProjectCode());

        projectBO.refreshSecretStatus(project.getCode(),
            ESecretStatus.YES.getCode());

        return projectConfigBO.saveProjectConfig(project, req);
    }

    @Override
    @Transactional
    public void editProjectConfig(XN631622Req req) {

        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByLocal(req.getLocalProjectCode());

        if (null == projectConfig) {

            ProjectConfig configByProject = projectConfigBO
                .getProjectConfigByProject(req.getProjectCode());
            if (null != configByProject) {
                throw new BizException("XN631620", "该项目配置已添加,无法再次添加");
            }

            Project project = projectBO.getProject(req.getLocalProjectCode());
            projectConfigBO.saveProjectConfig(project, req);

            projectBO.refreshSecretStatus(req.getLocalProjectCode(),
                ESecretStatus.YES.getCode());

        } else {

            ProjectConfig configByProject = projectConfigBO
                .getProjectConfigByProject(req.getProjectCode());
            if (null != configByProject && !req.getProjectCode()
                .equals(configByProject.getProjectCode())) {
                throw new BizException("XN631620", "该项目配置已添加,无法再次添加");
            }

            if (!projectConfig.getSecret().equals(req.getSecret())) {
                projectConfigBO.checkProjectConfigBySecret(req.getProjectCode(),
                    req.getSecret());
            }

            projectConfigBO.refreshProjectConfig(projectConfig.getCode(), req);

        }

    }

    @Override
    public int dropProjectConfig(String code) {
        return projectConfigBO.removeProjectConfig(code);
    }

    @Override
    @Transactional
    public void startStop(String code, String userId, String remark) {
        User operator = userBO.getBriefUser(userId);

        ProjectConfig projectConfig = projectConfigBO.getProjectConfig(code);

        String status = EProjectConfigStatus.START.getCode();
        String operate = EOperateLogOperate.StartProjectConfig.getValue();

        if (EProjectConfigStatus.START.getCode()
            .equals(projectConfig.getStatus())) {
            status = EProjectConfigStatus.STOP.getCode();
            operate = EOperateLogOperate.StopProjectConfig.getValue();
        }

        projectConfigBO.refreshStatus(code, status);

        operateLogBO.saveOperateLog(EOperateLogRefType.ProjectConfig.getCode(),
            code, operate, operator, remark);
    }

    @Override
    public Paginable<ProjectConfig> queryProjectConfigPage(int start, int limit,
            ProjectConfig condition) {
        return projectConfigBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<ProjectConfig> queryProjectConfigList(ProjectConfig condition) {
        return projectConfigBO.queryProjectConfigList(condition);
    }

    @Override
    public ProjectConfig getProjectConfig(String code) {
        return projectConfigBO.getProjectConfig(code);
    }

}
