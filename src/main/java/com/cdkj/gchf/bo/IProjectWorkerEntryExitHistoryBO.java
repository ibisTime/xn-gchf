package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorkerEntryExitHistory;
import com.cdkj.gchf.dto.req.XN631914Req;
import com.cdkj.gchf.dto.req.XN631915Req;

public interface IProjectWorkerEntryExitHistoryBO
        extends IPaginableBO<ProjectWorkerEntryExitHistory> {

    public String saveProjectWorkerEntryExitHistory(
            ProjectWorkerEntryExitHistory data);

    public int removeProjectWorkerEntryExitHistory(String code);

    public int refreshProjectWorkerEntryExitHistory(
            ProjectWorkerEntryExitHistory data);

    public List<ProjectWorkerEntryExitHistory> queryProjectWorkerEntryExitHistoryList(
            ProjectWorkerEntryExitHistory condition);

    public ProjectWorkerEntryExitHistory getProjectWorkerEntryExitHistory(
            String code);

    /****国家平台接口****/
    public void doUpload(XN631914Req req, ProjectConfig projectConfig);

    public Paginable<ProjectWorkerEntryExitHistory> doQuery(XN631915Req req,
            ProjectConfig projectConfig);
}
