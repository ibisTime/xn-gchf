package com.cdkj.gchf.ao.impl;

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
import com.cdkj.gchf.enums.EIdCardType;
import com.cdkj.gchf.enums.EOperateLogOperate;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EUploadStatus;
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
        if (data.getType() == Integer.parseInt(EEntryExitType.IN.getCode())) {
            ProjectWorkerEntryExitHistory lastTimeEntryTime = projectWorkerEntryExitHistoryBO
                .getLastTimeEntryTime(data.getWorkerCode());
            if (lastTimeEntryTime != null) {
                if (lastTimeEntryTime.getType() == Integer
                    .parseInt(EEntryExitType.IN.getCode())) {
                    throw new BizException("XN631730", "项目人员未退场无法添加进场信息");
                }
            }

        }
        return projectWorkerEntryExitHistoryBO
            .saveProjectWorkerEntryExitHistory(data);
    }

    @Override
    public void editProjectWorkerEntryExitHistory(XN631732Req req) {
        ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory = projectWorkerEntryExitHistoryBO
            .getProjectWorkerEntryExitHistory(req.getCode());
        if (projectWorkerEntryExitHistory.getUploadStatus()
            .equals(EUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
            throw new BizException("XN631732", "人员进退场已上传,不可编辑");
        }

        projectWorkerEntryExitHistoryBO
            .refreshProjectWorkerEntryExitHistory(req);
    }

    @Override
    public void dropProjectWorkerEntryExitHistory(String code) {
        ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory = projectWorkerEntryExitHistoryBO
            .getProjectWorkerEntryExitHistory(code);
        if (!projectWorkerEntryExitHistory.getUploadStatus()
            .equals(EUploadStatus.TO_UPLOAD.getCode())) {
            throw new BizException("XN631731", "人员进退场已上传，不可删除");
        }
        projectWorkerEntryExitHistoryBO
            .updateProjectWorkerEntryExitHistoryDeleteStatus(code,
                EDeleteStatus.DELETED.getCode());
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
        if (EUserKind.Plat.getCode().equals(user.getType())) {
            condition.setProjectCode(user.getOrganizationCode());
        }

        Paginable<ProjectWorkerEntryExitHistory> page = projectWorkerEntryExitHistoryBO
            .getPaginable(start, limit, condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory : page
                .getList()) {
                TeamMaster teamMaster = teamMasterBO.getTeamMaster(
                    projectWorkerEntryExitHistory.getTeamSysNo());
                projectWorkerEntryExitHistory
                    .setTeamName(teamMaster.getTeamName());

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
    public ProjectWorkerEntryExitHistory getProjectWorkerEntryExitHistory(
            String code) {
        return projectWorkerEntryExitHistoryBO
            .getProjectWorkerEntryExitHistory(code);
    }

    @Override
    public Object queryProjectWorkerEntryExitHistory(String code) {
        return projectWorkerEntryExitHistoryBO
            .queryProjectWorkerEntryExitHistory(code);
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
            if (projectConfigByLocal == null) {
                throw new BizException("XN631734", "项目编号不存在");
            }
            TeamMaster teamMaster = teamMasterBO
                .getTeamMaster(projectWorkerEntryExitHistory.getTeamSysNo());
            JsonObject requestJson = projectWorkerEntryExitHistoryBO
                .getRequestJson(teamMaster, projectWorkerEntryExitHistory,
                    projectConfigByLocal);

            String resString = GovConnecter.getGovData("WorkerEntryExit.Add",
                requestJson.toString(), projectConfigByLocal.getProjectCode(),
                projectConfigByLocal.getSecret());

            String operateLog = operateLogBO.saveOperateLog(
                EOperateLogRefType.ProjectWorkerEntryExitHistory.getCode(),
                code, EOperateLogOperate.UploadProjectWorkerEntryExitHistory
                    .getValue(),
                briefUser, null);
            AsyncQueueHolder.addSerial(resString, projectConfigByLocal,
                "projectWorkerEntryExitHistoryBO", code,
                EUploadStatus.UPLOAD_EDITABLE.getCode(), operateLog);

        }
    }

    /**
     * <p>Description: 导入人员进退场</p>   
     */
    @Transactional
    @Override
    public void importProjectWorkerEntryExitHistoryList(XN631733Req req) {
        User user = userBO.getBriefUser(req.getUpdater());

        for (XN631733ReqData data : req.getDateList()) {
            // 校验数据字典类型数据
            EIdCardType.checkExists(data.getIdcardType());
            EEntryExitType.checkExists(String.valueOf(data.getType()));

            TeamMaster teamMaster = teamMasterBO.getTeamMasterByProject(
                req.getProjectCode(), data.getCorpCode(), data.getTeamName());
            if (teamMaster == null) {
                throw new BizException("XN631733",
                    "项目班组【" + data.getTeamName() + "】未录入");
            }

            List<ProjectWorker> infoByIdCardNumber = projectWorkerBO
                .getProjectWorkerByIdentity(teamMaster.getCode(),
                    data.getIdcardType(), data.getIdcardNumber());
            if (infoByIdCardNumber == null) {
                throw new BizException("XN631733",
                    "项目人员【" + data.getIdcardNumber() + "】未录入");
            }
            String code = projectWorkerEntryExitHistoryBO
                .saveProjectWorkerEntryExitHistory(teamMaster,
                    infoByIdCardNumber.get(0), data);
            operateLogBO.saveOperateLog(
                EOperateLogRefType.WorkAttendance.getCode(), code, "导入人员进退场信息",
                user, null);
        }
    }

}
