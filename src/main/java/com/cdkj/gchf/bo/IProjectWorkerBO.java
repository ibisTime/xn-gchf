package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.dto.req.XN631690Req;
import com.cdkj.gchf.dto.req.XN631692Req;
import com.cdkj.gchf.dto.req.XN631911Req;
import com.cdkj.gchf.dto.req.XN631912Req;
import com.cdkj.gchf.dto.req.XN631913Req;
import com.google.gson.JsonObject;

public interface IProjectWorkerBO extends IPaginableBO<ProjectWorker> {

    public String saveProjectWorker(XN631690Req req);

    public String saveProjectWorker(ProjectWorker projectWorker);

    public String saveProjectWorker(String workerCode, String workerName,
            String idcardNumber, Project project);

    public void removeProjectWorker(String code);

    public void refreshProjectWorker(XN631692Req req, TeamMaster teamMaster);

    void updateProjectWorkerDeleteStatus(String code, String status);

    public void refreshUploadStatus(String code, String status);

    void fakeDeleteProjectWorker(String projectcode);

    void fakeDeleteProjectWorkerByTeamNo(String projectCode,
            String teamMasterNo);

    public List<ProjectWorker> queryProjectWorkerList(ProjectWorker condition);

    public List<ProjectWorker> queryProjectWorkerList(String projectCode,
            String idcardNumber);

    public List<ProjectWorker> queryProjectWorkerList(String teamMasterNo);

    public ProjectWorker getProjectWorker(String code);

    public List<ProjectWorker> getProjectWorker(String projectCode,
            String idCardNumber);

    public ProjectWorker getProjectWorker(String projectCode, String corpCode,
            String teamSysNo, String idcardNumber);

    public ProjectWorker getProjectWorkerByProjectCode(String code);

    int updateProjectWorkerStatus(String code, String status);

    public List<ProjectWorker> getProjectWorkerByIdentity(String teamMasterNo,
            String idCardNumber);

    public JsonObject getProjectWorkerJson(TeamMaster teamMaster,
            ProjectWorker projectWorker, ProjectConfig projectConfig);

    /****国家平台接口****/
    public void doUpload(XN631911Req req, ProjectConfig projectConfig);

    public void doUpdate(XN631912Req req, ProjectConfig projectConfig);

    public Paginable<ProjectWorker> doQuery(XN631913Req req,
            ProjectConfig projectConfig);
}
