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
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631620Req;
import com.cdkj.gchf.dto.req.XN631622Req;
import com.cdkj.gchf.enums.EOperateLogOperate;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EProjectConfigStatus;
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

        // if (null != projectConfigBO
        // .getProjectConfigByLocal(req.getLocalProjectCode())) {
        // throw new BizException("XN631620", "该项目已添加配置，无法再次添加");
        // }

        if (null != projectConfigBO
            .getProjectConfigByProject(req.getProjectCode())) {
            throw new BizException("XN631620", "该项目已添加配置，无法再次添加");
        }

        String projectCode = projectBO.saveProject(req.getProjectName());

        userBO.saveProjectAdmin(projectCode, req.getProjectName());

        return projectConfigBO.saveProjectConfig(projectCode, req);
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
