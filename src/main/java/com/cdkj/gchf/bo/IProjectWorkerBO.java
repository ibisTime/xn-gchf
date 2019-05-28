package com.cdkj.gchf.bo;

import com.cdkj.gchf.api.impl.XN631693ReqData;
import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.*;
import com.cdkj.gchf.dto.req.*;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

public interface IProjectWorkerBO extends IPaginableBO<ProjectWorker> {
    /**
     * 保存项目人员
     */
    ProjectWorker saveProjectWorker(XN631690Req req);

    /**
     * 添加项目人员（H5端用）
     *
     * @param project
     * @param teamMaster
     * @param workerInfo
     * @param req
     * @return ProjectWorker
     */
    ProjectWorker saveProjectWorker(Project project, TeamMaster teamMaster, WorkerInfo workerInfo, XN631696Req req);


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
     * 假删除项目人员
     */
    void fakeDeleteProjectWorker(String projectcode);

    /**
     * 根据班组编号删除项目人员
     */
    void fakeDeleteProjectWorkerByTeamNo(String projectCode,
                                         String teamMasterNo);

    /**
     * 修改项目人员
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
     * 修改员工进退场状态
     *
     * @param code
     * @param status 进退场状态
     */
    void refreshStatus(String code, String status);


    /**
     * 更新员工最近一次的工资单信息
     *
     * @param code                    主键code
     * @param lastPayMonth            最后一次发工资的月份
     * @param lastPayRollTotalAmount  最后一次的总发金额
     * @param lastPayRollActualAmount 最近一次的实发金额
     */
    void refreshLastPayRoll(String code, String lastPayMonth, String lastPayRollTotalAmount, String lastPayRollActualAmount);

    /**
     * 回写员工最近一次的进出记录
     *
     * @param code           主键code
     * @param status         进出状态（进场/出场）
     * @param recordDateTime 进出场时间
     */
    void refreshLastInOutRecord(String code, String status, String recordDateTime);


    /**
     * 回写员工最近一次的考勤时间
     *
     * @param code               主键code
     * @param attendanceStatus   考勤状态（进场/出场）
     * @param attendanceDateTime 考勤时间
     */
    void refreshLastAttendance(String code, String attendanceStatus, String attendanceDateTime);

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

    /**
     * 根据实名制code 查询项目人员
     *
     * @param workerCode
     * @return
     */
    List<ProjectWorker> selectProjectWorkerByWorkerCode(String workerCode);


    /**
     * 根据用户id查询工种分布
     *
     * @param userId 用户id
     */
    List<Map> selectProjectWorkerWorkerTyepSpread(String userId);


    /**
     * 根据用户id查年龄分布
     *
     * @param userId 用户id
     */
    List<Map> selectWorkerAgeInterval(String userId);

    /**
     * 查询在职、今日上班、总发薪
     */
    List<Map> selectWorkerData(String userId);



    /****国家平台接口****/
    public void doUpload(XN631911Req req, ProjectConfig projectConfig);

    public void doUpdate(XN631912Req req, ProjectConfig projectConfig);

    public Paginable<ProjectWorker> doQuery(XN631913Req req,
                                            ProjectConfig projectConfig);
}
