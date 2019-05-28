package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.*;
import com.cdkj.gchf.dto.req.XN631730Req;
import com.cdkj.gchf.dto.req.XN631732Req;
import com.cdkj.gchf.dto.req.XN631733ReqData;
import com.cdkj.gchf.dto.req.XN631914Req;
import com.cdkj.gchf.dto.req.XN631915Req;
import com.google.gson.JsonObject;
import jnr.ffi.annotations.In;

public interface IProjectWorkerEntryExitHistoryBO
        extends IPaginableBO<ProjectWorkerEntryExitHistory> {
    /**
     * 新增进退场
     */
    public String saveProjectWorkerEntryExitHistory(XN631730Req data);

    /**
     * 新增进退场
     */
    String saveProjectWorkerEntryExitHistory(TeamMaster master,
            ProjectWorker projectWorker, XN631733ReqData datas);

    /**
     * 批量插入人员进退场
     * @param user 用户 用于保存日志
     * @param dataList 具体数据
     * @param teamMasterList 班组列表
     * @param projectWorkerList 项目人员列表
     */
    void batchInsertWorkerEntryExitHistory(User user, List<XN631733ReqData> dataList , List<TeamMaster>  teamMasterList, List<ProjectWorker> projectWorkerList);
    /**
     * 查询最后一次进退场信息
     */
    public ProjectWorkerEntryExitHistory getLastTimeEntryTime(
            String workerCode);

    /**
     * 修改进退场信息
     */
    public void refreshProjectWorkerEntryExitHistory(XN631732Req data);

    /**
     * 更新进退场信息
     */
    void updateProjectWorkerEntryExitHistoryDeleteStatus(String code,
            String status);

    /**
     * 根据项目编号删除进退场信息
     */
    void fakeDeleteProjectWorkerEntryHistoryByProject(String ProjectCode);

    /**
     * 根据项目人员删除进退场信息
     */
    void fakeDeleteProjectWorkerEntryHistory(String workerCode);

    /**
     * 假删除进退场信息
     */
    void fakeDeleteProjectWorkerEntryHistoryByTeamMaster(String teamMasterNo);

    /**
     * 根据code查进退场信息
     */
    public ProjectWorkerEntryExitHistory queryProjectWorkerEntryExitHistory(
            String code);

    /**
     * 条件查进退场信息
     */
    public List<ProjectWorkerEntryExitHistory> queryProjectWorkerEntryExitHistoryList(
            ProjectWorkerEntryExitHistory condition);

    /**
     * code查 
     */
    public ProjectWorkerEntryExitHistory getProjectWorkerEntryExitHistory(
            String code);

    /**
     * 修改上传状态
     */
    public void refreshUploadStatus(String code, String status);


    /**
     * 查询30天项目离职人数
     */
    Integer selectProjectWorkerLeavingCount(String userId);

    /**
     * 查询30天入职人数
     */
    Integer selectProjectWorkerComingCount(String userId);
    /**
     * 获取上传国家平台json
     */
    public JsonObject getRequestJson(TeamMaster teamMaster,
            ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory,
            ProjectConfig projectConfigByLocal);

    /****国家平台接口****/
    public void doUpload(XN631914Req req, ProjectConfig projectConfig);

    /**
     * 分页查国家平台信息
     */
    public Paginable<ProjectWorkerEntryExitHistory> doQuery(XN631915Req req,
            ProjectConfig projectConfig);
}
