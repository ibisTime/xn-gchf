package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IProjectWorkerEntryExitHistoryAO;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.IProjectWorkerEntryExitHistoryBO;
import com.cdkj.gchf.bo.ITeamMasterBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.ProjectWorkerEntryExitHistory;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631730Req;
import com.cdkj.gchf.dto.req.XN631732Req;
import com.cdkj.gchf.dto.req.XN631733Req;
import com.cdkj.gchf.dto.req.XN631913Req;
import com.cdkj.gchf.dto.req.XN631914Req;
import com.cdkj.gchf.dto.req.XN631915Req;
import com.cdkj.gchf.enums.EOperateLogOperate;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.AsyncQueueHolder;
import com.cdkj.gchf.gov.GovConnecter;
import com.cdkj.gchf.gov.WorkerEntryExit;
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

    @Override
    public String addProjectWorkerEntryExitHistory(XN631730Req data) {
        ProjectWorker projectWorker = projectWorkerBO
            .getProjectWorker(data.getProjectWorkerCode());

        ProjectWorkerEntryExitHistory workerEntryExitHistory = projectWorkerEntryExitHistoryBO
            .getProjectWorkerEntryExitHistoryByIdCardNumber(
                projectWorker.getIdCardNumber());
        if (workerEntryExitHistory != null) {
            throw new BizException("XN631730", "人员进退场已添加");
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
        if (projectWorkerEntryExitHistory.getUploadStatus()
            .equals(EUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
            throw new BizException("XN631731", "人员进退场已上传，不可删除");
        }
        projectWorkerEntryExitHistoryBO
            .removeProjectWorkerEntryExitHistory(code);
    }

    @Override
    public void uploadProjectWorkerEntryExitHistory(XN631914Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631914", "该项目未配置，无法上传");
        }

        projectWorkerEntryExitHistoryBO.doUpload(req, projectConfig);
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

                XN631913Req workerReq = new XN631913Req(req.getProjectCode(),
                    projectWorkerEntryExitHistory.getCorpCode(),
                    projectWorkerEntryExitHistory.getIdcardNumber());
                workerReq.setPageIndex(0);
                workerReq.setPageSize(1);
                Paginable<ProjectWorker> projectWorker = projectWorkerBO
                    .doQuery(workerReq, projectConfig);
                if (null != projectWorker && CollectionUtils
                    .isNotEmpty(projectWorker.getList())) {
                    projectWorkerEntryExitHistory.setWorkerName(
                        projectWorker.getList().get(0).getWorkerName());
                }

                String idcardNumber = AesUtils.decrypt(
                    projectWorkerEntryExitHistory.getIdcardNumber(),
                    projectConfig.getSecret());

                projectWorkerEntryExitHistory.setIdcardNumber(idcardNumber);

            }
        }

        return page;
    }

    @Override
    public Paginable<ProjectWorkerEntryExitHistory> queryProjectWorkerEntryExitHistoryPage(
            int start, int limit, ProjectWorkerEntryExitHistory condition) {
        return projectWorkerEntryExitHistoryBO.getPaginable(start, limit,
            condition);
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
            projectWorkerEntryExitHistory.getProjectCode();
            ProjectConfig projectConfigByLocal = projectConfigBO
                .getProjectConfigByLocal(
                    projectWorkerEntryExitHistory.getProjectCode());

            TeamMaster teamMaster = teamMasterBO
                .getTeamMaster(projectWorkerEntryExitHistory.getTeamSysNo());

            JsonObject requestJson = projectWorkerEntryExitHistoryBO
                .getRequestJson(teamMaster, projectWorkerEntryExitHistory,
                    projectConfigByLocal);
            System.out.println(requestJson.toString());

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
                EUploadStatus.UPLOAD_UNEDITABLE.getCode(), operateLog);

        }
    }

    @Override
    public void importProjectWorkerEntryExitHistoryList(XN631733Req req) {

        for (WorkerEntryExit entryExit : req.getWorkerEntryExitHistoryList()) {
            XN631730Req data = new XN631730Req();
            BeanUtils.copyProperties(entryExit, data);
            projectWorkerEntryExitHistoryBO
                .saveProjectWorkerEntryExitHistory(data);
        }

    }

}
