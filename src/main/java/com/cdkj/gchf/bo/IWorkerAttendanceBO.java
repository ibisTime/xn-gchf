package com.cdkj.gchf.bo;

import java.util.Date;
import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.EquipmentInfo;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.domain.WorkerAttendance;
import com.cdkj.gchf.domain.WorkerEntryExitRecord;
import com.cdkj.gchf.dto.req.XN631710Req;
import com.cdkj.gchf.dto.req.XN631712Req;
import com.cdkj.gchf.dto.req.XN631713ReqData;
import com.cdkj.gchf.dto.req.XN631918Req;
import com.cdkj.gchf.dto.req.XN631919Req;
import com.google.gson.JsonObject;

public interface IWorkerAttendanceBO extends IPaginableBO<WorkerAttendance> {

    String saveWorkerAttendance(XN631710Req data, TeamMaster teamMaster);

    String saveWorkerAttendance(WorkerAttendance workerAttendance);

    String saveWorkerAttendance(String projectCode, XN631713ReqData data,
                                ProjectWorker projectWorker, WorkerAttendance workerAttendance);

    void deleteWorkerAttendance(String code);

    WorkerAttendance select(WorkerAttendance condition);


    void deleteWorkerAttendaceByWorkerCode(String workerCode);

    /**
     * 批量删除人员考勤
     *
     * @param codes 主键列表
     */
    void batchDeleteWorkerAttendance(List<String> codes);

    // 每日自动上传的考勤记录
    String addWorkerAttendace(
            WorkerEntryExitRecord workerEntryExitRecord,
            EquipmentInfo equipmentInfo, Date time, String photoUrl,
            String type);

    public String saveWorkerAttendance(Project project, TeamMaster teamMaster,
            ProjectWorker projectWorker, Date date, String direction);

    // 保存人员考勤-人脸
    WorkerAttendance saveDeviceWorkerAttendance(ProjectWorker projectWorker,
                                                String deviceKey, String dateTime, String photoUrl, String type,
                                                String dataString, String recMode, String idCardInfo);


    int deleteWorkAttendanceByProject(String projectCode);

    int deleteWorkAttendanceByTeamMaster(String teamMasterNo);

    int deleteWorkAttendanceByWorkerCode(String workerCode);

    void refreshUploadStatus(String code, String uploadStatus);

    public void refreshWorkerAttendance(XN631712Req data);

    void refreshWorkerAttendance(String code, String status);

    int updateWorkerAttendanceDeleteStatus(String code, String status);

    void refreshWorkerAttendanceTeamName(String teamSysNo, String teamName);

    public List<WorkerAttendance> queryWorkerAttendanceList(
            WorkerAttendance condition);

    public WorkerAttendance getWorkerAttendance(String code);


    /**
     * 查询30天考勤人数
     */
    Integer selectWorkerAttendance30Day(String userId);


    /**
     * 查询当天考勤人数
     */
    Integer selectWorkerAttendanceToday(String userId);

    /**
     * 获取上传国家平台json
     */
    public JsonObject getRequestJson(TeamMaster teamMaster,
                                     WorkerAttendance workerAttendance,
                                     ProjectConfig projectConfigByLocal);


    /**
     * 查询项目人员最新一条记录
     *
     * @param workerCode 项目人员code
     * @return data
     */
    WorkerAttendance getLastAttendance(String workerCode);

    /****国家平台接口****/
    public void doUpload(XN631918Req req, ProjectConfig projectConfig);

    public Paginable<WorkerAttendance> doQuery(XN631919Req req,
            ProjectConfig projectConfig);
}
