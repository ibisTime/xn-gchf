package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorkerEntryExitHistory;
import com.cdkj.gchf.dto.req.XN631730Req;
import com.cdkj.gchf.dto.req.XN631732Req;
import com.cdkj.gchf.dto.req.XN631914Req;
import com.cdkj.gchf.dto.req.XN631915Req;

public interface IProjectWorkerEntryExitHistoryBO
        extends IPaginableBO<ProjectWorkerEntryExitHistory> {

    public String saveProjectWorkerEntryExitHistory(XN631730Req data);

    public void removeProjectWorkerEntryExitHistory(String code);

    public void refreshProjectWorkerEntryExitHistory(XN631732Req data);

    public Object queryProjectWorkerEntryExitHistory(String code);

    public List<ProjectWorkerEntryExitHistory> queryProjectWorkerEntryExitHistoryList(
            ProjectWorkerEntryExitHistory condition);

    public ProjectWorkerEntryExitHistory getProjectWorkerEntryExitHistory(
            String code);

    /****国家平台接口****/
    public void doUpload(XN631914Req req, ProjectConfig projectConfig);

    public Paginable<ProjectWorkerEntryExitHistory> doQuery(XN631915Req req,
            ProjectConfig projectConfig);
}
