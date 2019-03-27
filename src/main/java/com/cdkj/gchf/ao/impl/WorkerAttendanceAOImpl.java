package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IWorkerAttendanceAO;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.ITeamMasterBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.IWorkerAttendanceBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.domain.WorkerAttendance;
import com.cdkj.gchf.dto.req.XN631710Req;
import com.cdkj.gchf.dto.req.XN631712Req;
import com.cdkj.gchf.dto.req.XN631918Req;
import com.cdkj.gchf.dto.req.XN631919Req;
import com.cdkj.gchf.enums.EOperateLogOperate;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.AsyncQueueHolder;
import com.cdkj.gchf.gov.GovConnecter;
import com.google.gson.JsonObject;

@Service
public class WorkerAttendanceAOImpl implements IWorkerAttendanceAO {

    @Autowired
    IWorkerAttendanceBO workerAttendanceBO;

    @Autowired
    private IProjectConfigBO projectConfigBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IOperateLogBO operateLogBO;

    @Autowired
    private ITeamMasterBO teamMasterBO;

    @Override
    public String addWorkerAttendance(XN631710Req data) {
        return workerAttendanceBO.saveWorkerAttendance(data);
    }

    @Override
    public void editWorkerAttendance(XN631712Req data) {
        workerAttendanceBO.refreshWorkerAttendance(data);
    }

    @Override
    public int dropWorkerAttendance(String code) {
        return workerAttendanceBO.removeWorkerAttendance(code);
    }

    @Override
    public void uploadWorkerAttendance(XN631918Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631918", "该项目未配置，无法上传");
        }

        workerAttendanceBO.doUpload(req, projectConfig);
    }

    @Override
    public Paginable<WorkerAttendance> queryWorkerAttendance(XN631919Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631919", "该项目未配置，无法查询");
        }

        return workerAttendanceBO.doQuery(req, projectConfig);
    }

    @Override
    public Paginable<WorkerAttendance> queryWorkerAttendancePage(int start,
            int limit, WorkerAttendance condition) {
        return workerAttendanceBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<WorkerAttendance> queryWorkerAttendanceList(
            WorkerAttendance condition) {
        return workerAttendanceBO.queryWorkerAttendanceList(condition);
    }

    @Override
    public WorkerAttendance getWorkerAttendance(String code) {
        return workerAttendanceBO.getWorkerAttendance(code);
    }

    @Override
    public void uploadWorkerAttendanceList(String userId,
            List<String> codeList) {
        User briefUser = userBO.getBriefUser(userId);
        for (String code : codeList) {
            WorkerAttendance workerAttendance = workerAttendanceBO
                .getWorkerAttendance(code);

            ProjectConfig projectConfigByLocal = projectConfigBO
                .getProjectConfigByLocal(workerAttendance.getProjectCode());

            if (projectConfigByLocal == null) {
                throw new BizException("XN631714", "该项目未配置，无法查询");
            }
            TeamMaster teamMaster = teamMasterBO
                .getTeamMaster(workerAttendance.getTeamSysNo());

            JsonObject requestJson = workerAttendanceBO.getRequestJson(
                teamMaster, workerAttendance, projectConfigByLocal);
            System.out.println(requestJson);
            String resString = GovConnecter.getGovData("WorkerAttendance.Add",
                requestJson.toString(), projectConfigByLocal.getProjectCode(),
                projectConfigByLocal.getSecret());

            String saveOperateLog = operateLogBO.saveOperateLog(
                EOperateLogRefType.WorkAttendance.getCode(), code,
                EOperateLogOperate.UploadWorkAtendance.getValue(), briefUser,
                null);

            AsyncQueueHolder.addSerial(resString, projectConfigByLocal,
                "workerAttendanceBO", code,
                EUploadStatus.UPLOAD_UNEDITABLE.getCode(), saveOperateLog);

        }
    }

}
