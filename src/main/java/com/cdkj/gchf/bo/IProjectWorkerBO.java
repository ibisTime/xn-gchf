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
    /**
     * 保存项目人员 
     */
    String saveProjectWorker(XN631690Req req);


    /**
     * 保存项目人员-导入
     */
    String saveProjectWorker(WorkerInfo workerInfo, XN631693ReqData req,
            Project project, TeamMaster teamMaster, CorpBasicinfo corpBasic);

    /**
     * 保存项目人员-导入 
     */
    String saveProjectWorker(String workerCode, Project project,
            CorpBasicinfo corpBasicinfo, TeamMaster teamName,
            XN631693ReqData req);


    /**
     *修改项目人员 
     */
    public void refreshProjectWorker(XN631692Req req, TeamMaster teamMaster);

    /**
     * @Description: 建档人员 更新项目人员
     */
    void refreshWorkerIdCardNumber(String workerCode, String newIdCardNumber,
            String workerName);

    /**
     * 修改项目人员手机号 
     */
    void refreshWorkerCelephone(String workerCode, String phone);

    /**
     * 修改项目人员班组名称 
     */
    public void refreshProjectWorkerTeamName(String teamNo, String teamName);

    /**
     * 修改删除状态
     */
    void updateProjectWorkerDeleteStatus(String code, String status);

    /**
     * 修改上传状态 
     */
    public void refreshUploadStatus(String code, String status);

    /**
     * 假删除项目人员 
     */
    void fakeDeleteProjectWorker(String projectcode);

    /**
     * 根据班组编号删除项目人员 
     */
    void fakeDeleteProjectWorkerByTeamNo(String projectCode,
            String teamMasterNo);

    public List<ProjectWorker> queryProjectWorkerList(ProjectWorker condition);


    List<ProjectWorker> queryProjectWorkerListByProject(String projectCode);

    public List<ProjectWorker> queryUploadedProjectWorkerList(
            String teamMasterNo);

    public ProjectWorker getProjectWorker(String code);

    public ProjectWorker getProjectWorker(String projectCode,
            String idcardNumber);

    /**
     * 查询项目人员 
     */
    List<ProjectWorker> getProjectWorkers(String corpCode,
            String teamMasterName, String workerName);

    /**
     * 根据guid查询项目人员  
     */
    ProjectWorker getProjectWorkerByGuid(String guid, String deviceKey);

    /**
     * 根据projectCode查询项目人员 
     */
    public List<ProjectWorker> getProjectWorkerByProjectCode(String projectCode,
            String uploadStatus);

    /**
     * 修改项目人员上传状态 
     */
    int updateProjectWorkerUploadStatus(String code, String status);

    /**
     * 通过身份证号查询项目人员 
     */
    public ProjectWorker getProjectWorkerByIdentity(String teamMasterNo,
            String idCardNumber);

    /**
     * 获取上传json 
     */
    public JsonObject getProjectWorkerJson(TeamMaster teamMaster,
            ProjectWorker projectWorker, ProjectConfig projectConfig);

    /****国家平台接口****/
    public void doUpload(XN631911Req req, ProjectConfig projectConfig);

    public void doUpdate(XN631912Req req, ProjectConfig projectConfig);

    public Paginable<ProjectWorker> doQuery(XN631913Req req,
            ProjectConfig projectConfig);
}
