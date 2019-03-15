package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IProjectConfigAO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.dto.req.XN631620Req;
import com.cdkj.gchf.dto.req.XN631622Req;
import com.cdkj.gchf.exception.BizException;

@Service
public class ProjectConfigAOImpl implements IProjectConfigAO {

    @Autowired
    private IProjectConfigBO projectConfigBO;

    @Override
    public String addProjectConfig(XN631620Req req) {

        if (null != projectConfigBO
            .getProjectConfigByLocal(req.getLocalProjectCode())) {
            throw new BizException("XN631620", "该项目已添加配置，无法再次添加");
        }

        if (null != projectConfigBO
            .getProjectConfigByProject(req.getProjectCode())) {
            throw new BizException("XN631620", "该项目已添加配置，无法再次添加");
        }

        return projectConfigBO.saveProjectConfig(req);
    }

    @Override
    public void editProjectConfig(XN631622Req req) {
        projectConfigBO.refreshProjectConfig(req);
    }

    @Override
    public int dropProjectConfig(String code) {
        return projectConfigBO.removeProjectConfig(code);
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
