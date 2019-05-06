package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.api.impl.XN631693ReqData;
import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.domain.WorkerInfo;
import com.cdkj.gchf.dto.req.XN631690Req;
import com.cdkj.gchf.dto.req.XN631692Req;
import com.cdkj.gchf.dto.req.XN631911Req;
import com.cdkj.gchf.dto.req.XN631912Req;
import com.cdkj.gchf.dto.req.XN631913Req;
import com.google.gson.JsonObject;

public interface IProjectWorkerBO extends IPaginableBO<ProjectWorker> {

    public String saveProjectWorker(XN631690Req req);

    public String saveProjectWorker(ProjectWorker projectWorker);

    String saveProjectWorker(WorkerInfo workerInfo, XN631693ReqData req,
            Project project, TeamMaster teamMaster, CorpBasicinfo corpBasic);

    public String saveProjectWorker(String workerCode, String workerName,
            String idcardNumber, Project project);

    String saveProjectWorker(String workerCode, Project project,
            CorpBasicinfo corpBasicinfo, TeamMaster teamName,
            XN631693ReqData req);

    public void removeProjectWorker(String code);

    public void refreshProjectWorker(XN631692Req req, TeamMaster teamMaster);

    /**
     * @Description: 建档人员 更新项目人员
     */
    void refreshWorkerIdCardNumber(String workerCode, String newIdCardNumber,
            String workerName);

    void refreshWorkerCelephone(String workerCode, String phone);

    public void refreshProjectWorkerTeamName(String teamNO, String teamName);

    void refreshIdCardInfo(String oldIdCardNum, String newIdCardNum,
            String name);

    void updateProjectWorkerDeleteStatus(String code, String status);

    public void refreshUploadStatus(String code, String status);

    void fakeDeleteProjectWorker(String projectcode);

    void fakeDeleteProjectWorkerByTeamNo(String projectCode,
            String teamMasterNo);

    public List<ProjectWorker> queryProjectWorkerList(ProjectWorker condition);

    public List<ProjectWorker> queryProjectWorkerList(String projectCode,
            String idcardNumber);

    List<ProjectWorker> queryProjectWorkerListByProject(String projectCode);

    public List<ProjectWorker> queryUploadedProjectWorkerList(
            String teamMasterNo);

    public ProjectWorker getProjectWorker(String code);

    public ProjectWorker getProjectWorker(String projectCode,
            String idcardNumber);

    public ProjectWorker getProjectWorkerByProjectCode(String code);

    int updateProjectWorkerStatus(String code, String status);

    public ProjectWorker getProjectWorkerByIdentity(String teamMasterNo,
            String idCardNumber);

    public JsonObject getProjectWorkerJson(TeamMaster teamMaster,
            ProjectWorker projectWorker, ProjectConfig projectConfig);

    /****国家平台接口****/
    public void doUpload(XN631911Req req, ProjectConfig projectConfig);

    public void doUpdate(XN631912Req req, ProjectConfig projectConfig);

    public Paginable<ProjectWorker> doQuery(XN631913Req req,
            ProjectConfig projectConfig);
}
