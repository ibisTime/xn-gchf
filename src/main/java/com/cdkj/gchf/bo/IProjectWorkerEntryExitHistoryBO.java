package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.ProjectWorkerEntryExitHistory;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.dto.req.XN631730Req;
import com.cdkj.gchf.dto.req.XN631732Req;
import com.cdkj.gchf.dto.req.XN631733ReqData;
import com.cdkj.gchf.dto.req.XN631914Req;
import com.cdkj.gchf.dto.req.XN631915Req;
import com.google.gson.JsonObject;

public interface IProjectWorkerEntryExitHistoryBO
        extends IPaginableBO<ProjectWorkerEntryExitHistory> {

    public String saveProjectWorkerEntryExitHistory(XN631730Req data);

    public String saveProjectWorkerEntryExitHistory(
            ProjectWorkerEntryExitHistory entryExitHistory);

    String saveProjectWorkerEntryExitHistory(TeamMaster master,
            ProjectWorker projectWorker, XN631733ReqData datas);

    public ProjectWorkerEntryExitHistory getLastTimeEntryTime(
            String workerCode);

    public void removeProjectWorkerEntryExitHistory(String code);

    public void refreshProjectWorkerEntryExitHistory(XN631732Req data);

    void updateProjectWorkerEntryExitHistoryDeleteStatus(String code,
            String status);

    void fakeDeleteProjectWorkerEntryHistoryByProject(String ProjectCode);

    void fakeDeleteProjectWorkerEntryHistory(String workerCode);

    void fakeDeleteProjectWorkerEntryHistoryByTeamMaster(String teamMasterNo);

    public ProjectWorkerEntryExitHistory queryProjectWorkerEntryExitHistory(
            String code);

    public List<ProjectWorkerEntryExitHistory> queryProjectWorkerEntryExitHistoryList(
            ProjectWorkerEntryExitHistory condition);

    public ProjectWorkerEntryExitHistory getProjectWorkerEntryExitHistory(
            String code);

    public void refreshUploadStatus(String code, String status);

    public ProjectWorkerEntryExitHistory getProjectWorkerEntryExitHistoryByIdCardNumber(
            String idCardNumber);

    public JsonObject getRequestJson(TeamMaster teamMaster,
            ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory,
            ProjectConfig projectConfigByLocal);

    /****国家平台接口****/
    public void doUpload(XN631914Req req, ProjectConfig projectConfig);

    public Paginable<ProjectWorkerEntryExitHistory> doQuery(XN631915Req req,
            ProjectConfig projectConfig);
}
