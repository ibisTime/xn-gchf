package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.ao.IProjectWorkerEntryExitHistoryAO;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.IProjectWorkerEntryExitHistoryBO;
import com.cdkj.gchf.bo.ITeamMasterBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.ProjectWorkerEntryExitHistory;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631730Req;
import com.cdkj.gchf.dto.req.XN631732Req;
import com.cdkj.gchf.dto.req.XN631733Req;
import com.cdkj.gchf.dto.req.XN631733ReqData;
import com.cdkj.gchf.dto.req.XN631913Req;
import com.cdkj.gchf.dto.req.XN631914Req;
import com.cdkj.gchf.dto.req.XN631914ReqWorker;
import com.cdkj.gchf.dto.req.XN631915Req;
import com.cdkj.gchf.enums.EDeleteStatus;
import com.cdkj.gchf.enums.EEntryExitType;
import com.cdkj.gchf.enums.EOperateLogOperate;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EProjectWorkerEntryExitUploadStatus;
import com.cdkj.gchf.enums.EProjectWorkerUploadStatus;
import com.cdkj.gchf.enums.EUserKind;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.AsyncQueueHolder;
import com.cdkj.gchf.gov.GovConnecter;
import com.cdkj.gchf.gov.SerialHandler;
import com.google.gson.JsonObject;

@Service
public class ProjectWorkerEntryExitHistoryAOImpl
        implements IProjectWorkerEntryExitHistoryAO {

    @Autowired
    private IProjectWorkerEntryExitHistoryBO projectWorkerEntryExitHistoryBO;

    @Autowired
    private IProjectConfigBO projectConfigBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IOperateLogBO operateLogBO;

    @Autowired
    private IProjectWorkerBO projectWorkerBO;

    @Autowired
    private ITeamMasterBO teamMasterBO;

    @Autowired
    private IProjectBO projectBO;

    @Override
    public String addProjectWorkerEntryExitHistory(XN631730Req data) {
        ProjectWorker projectWorker = projectWorkerBO
            .getProjectWorker(data.getWorkerCode());
        if (projectWorker == null) {
            throw new BizException("XN631730", "员工信息不存在");
        }
        if (EEntryExitType.IN.getCode().equals(data.getType())) {
            ProjectWorkerEntryExitHistory lastTimeEntryTime = projectWorkerEntryExitHistoryBO
                .getLastTimeEntryTime(data.getWorkerCode());
            if (lastTimeEntryTime != null) {
                if (lastTimeEntryTime.getType() == Integer
                    .parseInt(EEntryExitType.IN.getCode())) {
                    throw new BizException("XN631730", "项目人员未退场无法添加进场信息");
                }
            }

        }
        String code = projectWorkerEntryExitHistoryBO
                .saveProjectWorkerEntryExitHistory(data);

        projectWorkerBO.refreshStatus(projectWorker.getCode(), data.getType());
        return code;
    }

    @Override
    public void editProjectWorkerEntryExitHistory(XN631732Req req) {
        ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory = projectWorkerEntryExitHistoryBO
            .getProjectWorkerEntryExitHistory(req.getCode());
        if (projectWorkerEntryExitHistory.getUploadStatus().equals(
            EProjectWorkerEntryExitUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
            throw new BizException("XN631732", "人员进退场已上传,不可编辑");
        }
        ProjectWorkerEntryExitHistory exitHistory = projectWorkerEntryExitHistoryBO.getProjectWorkerEntryExitHistory(req.getCode());
        //更新进退场信息
        projectWorkerEntryExitHistoryBO
            .refreshProjectWorkerEntryExitHistory(req);
        //更新进退场时间
        projectWorkerBO.refreshStatus(exitHistory.getWorkerCode(), req.getType());
    }

    @Override
    public void dropProjectWorkerEntryExitHistory(List<String> codeList) {
        for (String code : codeList) {
            ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory = projectWorkerEntryExitHistoryBO
                .getProjectWorkerEntryExitHistory(code);
            if (projectWorkerEntryExitHistory.getUploadStatus()
                .equals(EProjectWorkerEntryExitUploadStatus.UPLOAD_UNEDITABLE
                    .getCode())) {
                throw new BizException("XN631731", "人员进退场已上传，不可删除");
            }
            projectWorkerEntryExitHistoryBO
                .updateProjectWorkerEntryExitHistoryDeleteStatus(code,
                    EDeleteStatus.DELETED.getCode());
        }

    }

    /**
     * <p>Description: 导入人员进退场</p>   
     */
    @Transactional
    @Override
    public void importProjectWorkerEntryExitHistoryList(XN631733Req req) {
        User user = userBO.getBriefUser(req.getUpdater());

        List<ProjectWorker > projectWorkers = new ArrayList<>();
        List<TeamMaster> teamMasters = new ArrayList<>();
        for (XN631733ReqData data : req.getDateList()) {
            // 校验数据字典类型数据
            EEntryExitType.checkExists(String.valueOf(data.getType()));

            TeamMaster teamMaster = teamMasterBO.getTeamMasterByProject(
                req.getProjectCode(), data.getCorpCode(), data.getTeamName());
            if (teamMaster == null) {
                throw new BizException("XN631733",
                    "项目班组【" + data.getTeamName() + "】未录入");
            }

            ProjectWorker infoByIdCardNumber = projectWorkerBO
                .getProjectWorkerByIdentity(teamMaster.getCode(),
                    data.getIdcardNumber());
            if (infoByIdCardNumber == null) {
                throw new BizException("XN631733",
                    "项目人员【" + data.getIdcardNumber() + "】未录入");
            }
            teamMasters.add(teamMaster);
            projectWorkers.add(infoByIdCardNumber);
        }

        projectWorkerEntryExitHistoryBO.batchInsertWorkerEntryExitHistory(user,req.getDateList(),teamMasters,projectWorkers);
    }

    @Transactional
    @Override
    public void uploadProjectWorkerEntryExitHistoryList(String userId,
            List<String> codeList) {
        User briefUser = userBO.getBriefUser(userId);
        for (String code : codeList) {
            ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory = projectWorkerEntryExitHistoryBO
                .getProjectWorkerEntryExitHistory(code);
            ProjectConfig projectConfigByLocal = projectConfigBO
                .getProjectConfigByLocal(
                    projectWorkerEntryExitHistory.getProjectCode());
            // 校验项目配置
            Project project = projectBO
                .getProject(projectWorkerEntryExitHistory.getProjectCode());
            if (projectConfigByLocal == null) {
                throw new BizException("XN631734", "项目未配置" + project.getName());
            }

            // 校验项目人员是否上传

            ProjectWorker projectWorker = projectWorkerBO.getProjectWorker(
                projectWorkerEntryExitHistory.getWorkerCode());
            if (!projectWorker.getUploadStatus()
                .equals(EProjectWorkerUploadStatus.UPLOAD_UPDATE.getCode())
                    && !projectWorker.getUploadStatus().equals(
                        EProjectWorkerUploadStatus.UPLOAD_UNUPDATE.getCode())) {
                throw new BizException("XN00000",
                    "项目人员未上传【  " + projectWorker.getWorkerName() + " 】");
            }

            TeamMaster teamMaster = teamMasterBO
                .getTeamMaster(projectWorkerEntryExitHistory.getTeamSysNo());
            // 获取上传json
            JsonObject requestJson = projectWorkerEntryExitHistoryBO
                .getRequestJson(teamMaster, projectWorkerEntryExitHistory,
                    projectConfigByLocal);
            // 更新状态为上传中
            projectWorkerEntryExitHistoryBO.refreshUploadStatus(code,
                EProjectWorkerEntryExitUploadStatus.UPLOADING.getCode());

            String resString;
            try {
                // 捕捉国家平台抛出的异常 更新数据状态为上传失败
                resString = GovConnecter.getGovData("WorkerEntryExit.Add",
                    requestJson.toString(),
                    projectConfigByLocal.getProjectCode(),
                    projectConfigByLocal.getSecret());
            } catch (BizException e) {
                projectWorkerBO.refreshUploadStatus(code,
                    EProjectWorkerEntryExitUploadStatus.UPLOAD_FAIL.getCode());
                e.printStackTrace();
                throw e;
            }

            String operateLog = operateLogBO.saveOperateLog(
                EOperateLogRefType.ProjectWorkerEntryExitHistory.getCode(),
                code,
                EOperateLogOperate.UploadProjectWorkerEntryExitHistory
                    .getValue(),
                briefUser,
                EOperateLogOperate.UploadProjectWorkerEntryExitHistory
                    .getValue());
            AsyncQueueHolder.addSerial(resString, projectConfigByLocal,
                "projectWorkerEntryExitHistoryBO", code,
                EProjectWorkerEntryExitUploadStatus.UPLOAD_UNEDITABLE.getCode(),
                operateLog, userId);

        }
    }

    @Override
    public void uploadProjectWorkerEntryExitHistory(XN631914Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631914", "该项目未配置，无法上传");
        }
        List<XN631914ReqWorker> workerList = req.getWorkerList();
        for (XN631914ReqWorker worker : workerList) {
            worker.setIdCardNumber(AesUtils.encrypt(worker.getIdCardNumber(),
                projectConfig.getSecret()));
        }
        String data = JSONObject.toJSONStringWithDateFormat(req, "yyyy-MM-dd")
            .toString();

        String resString = GovConnecter.getGovData("WorkerEntryExit.Add", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());

        SerialHandler.handle(resString, projectConfig);
    }

    @Override
    public Paginable<ProjectWorkerEntryExitHistory> queryProjectWorkerEntryExitHistory(
            XN631915Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631915", "该项目未配置，无法查询");
        }

        Paginable<ProjectWorkerEntryExitHistory> page = projectWorkerEntryExitHistoryBO
            .doQuery(req, projectConfig);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory : page
                .getList()) {

                String idcardNumber = AesUtils.decrypt(
                    projectWorkerEntryExitHistory.getIdcardNumber(),
                    projectConfig.getSecret());

                XN631913Req workerReq = new XN631913Req(req.getProjectCode(),
                    projectWorkerEntryExitHistory.getCorpCode(), idcardNumber);
                workerReq.setPageIndex(0);
                workerReq.setPageSize(1);
                Paginable<ProjectWorker> projectWorker = projectWorkerBO
                    .doQuery(workerReq, projectConfig);
                if (null != projectWorker && CollectionUtils
                    .isNotEmpty(projectWorker.getList())) {
                    projectWorkerEntryExitHistory.setWorkerName(
                        projectWorker.getList().get(0).getWorkerName());
                }

                projectWorkerEntryExitHistory.setIdcardNumber(idcardNumber);

            }
        }

        return page;
    }

    @Override
    public Paginable<ProjectWorkerEntryExitHistory> queryProjectWorkerEntryExitHistoryPage(
            int start, int limit, ProjectWorkerEntryExitHistory condition) {

        User user = userBO.getBriefUser(condition.getUserId());
        if (EUserKind.Owner.getCode().equals(user.getType())) {
            condition.setProjectCode(user.getOrganizationCode());
        }

        Paginable<ProjectWorkerEntryExitHistory> page = projectWorkerEntryExitHistoryBO
            .getPaginable(start, limit, condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory : page
                .getList()) {
                TeamMaster teamMaster = teamMasterBO.getTeamMaster(
                    projectWorkerEntryExitHistory.getTeamSysNo());
                if (teamMaster != null) {
                    projectWorkerEntryExitHistory
                        .setTeamName(teamMaster.getTeamName());
                }
                Project project = projectBO
                    .getProject(teamMaster.getProjectCode());
                projectWorkerEntryExitHistory.setProjectName(project.getName());
            }
        }

        return page;
    }

    @Override
    public List<ProjectWorkerEntryExitHistory> queryProjectWorkerEntryExitHistoryList(
            ProjectWorkerEntryExitHistory condition) {
        return projectWorkerEntryExitHistoryBO
            .queryProjectWorkerEntryExitHistoryList(condition);
    }


    @Override
    public Object queryProjectWorkerEntryExitHistory(String code) {
        ProjectWorkerEntryExitHistory queryProjectWorkerEntryExitHistory = projectWorkerEntryExitHistoryBO
            .queryProjectWorkerEntryExitHistory(code);

        Project project = projectBO
            .getProject(queryProjectWorkerEntryExitHistory.getProjectCode());
        TeamMaster teamMaster = teamMasterBO
            .getTeamMaster(queryProjectWorkerEntryExitHistory.getTeamSysNo());
        queryProjectWorkerEntryExitHistory.setProjectName(project.getName());
        queryProjectWorkerEntryExitHistory
            .setTeamName(teamMaster.getTeamName());

        return queryProjectWorkerEntryExitHistory;
    }

}
