package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IProjectWorkerEntryExitHistoryAO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.IProjectWorkerEntryExitHistoryBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorkerEntryExitHistory;
import com.cdkj.gchf.dto.req.XN631730Req;
import com.cdkj.gchf.dto.req.XN631732Req;
import com.cdkj.gchf.dto.req.XN631914Req;
import com.cdkj.gchf.dto.req.XN631915Req;
import com.cdkj.gchf.exception.BizException;

@Service
public class ProjectWorkerEntryExitHistoryAOImpl
        implements IProjectWorkerEntryExitHistoryAO {

    @Autowired
    private IProjectWorkerEntryExitHistoryBO projectWorkerEntryExitHistoryBO;

    @Autowired
    private IProjectConfigBO projectConfigBO;

    @Override
    public String addProjectWorkerEntryExitHistory(XN631730Req data) {
        return projectWorkerEntryExitHistoryBO
            .saveProjectWorkerEntryExitHistory(data);
    }

    @Override
    public void editProjectWorkerEntryExitHistory(XN631732Req req) {
        projectWorkerEntryExitHistoryBO
            .refreshProjectWorkerEntryExitHistory(req);
    }

    @Override
    public void dropProjectWorkerEntryExitHistory(String code) {
        projectWorkerEntryExitHistoryBO
            .removeProjectWorkerEntryExitHistory(code);
    }

    @Override
    public void uploadProjectWorkerEntryExitHistory(XN631914Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631914", "该项目未配置，无法上传");
        }

        projectWorkerEntryExitHistoryBO.doUpload(req, projectConfig);
    }

    @Override
    public Paginable<ProjectWorkerEntryExitHistory> queryProjectWorkerEntryExitHistory(
            XN631915Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631915", "该项目未配置，无法查询");
        }

        return projectWorkerEntryExitHistoryBO.doQuery(req, projectConfig);
    }

    @Override
    public Paginable<ProjectWorkerEntryExitHistory> queryProjectWorkerEntryExitHistoryPage(
            int start, int limit, ProjectWorkerEntryExitHistory condition) {
        return projectWorkerEntryExitHistoryBO.getPaginable(start, limit,
            condition);
    }

    @Override
    public List<ProjectWorkerEntryExitHistory> queryProjectWorkerEntryExitHistoryList(
            ProjectWorkerEntryExitHistory condition) {
        return projectWorkerEntryExitHistoryBO
            .queryProjectWorkerEntryExitHistoryList(condition);
    }

    @Override
    public ProjectWorkerEntryExitHistory getProjectWorkerEntryExitHistory(
            String code) {
        return projectWorkerEntryExitHistoryBO
            .getProjectWorkerEntryExitHistory(code);
    }

    @Override
    public Object queryProjectWorkerEntryExitHistory(String code) {
        return projectWorkerEntryExitHistoryBO
            .queryProjectWorkerEntryExitHistory(code);
    }

}
