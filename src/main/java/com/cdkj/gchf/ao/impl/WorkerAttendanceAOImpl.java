package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IWorkerAttendanceAO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.ITeamMasterBO;
import com.cdkj.gchf.bo.IWorkerAttendanceBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.domain.WorkerAttendance;
import com.cdkj.gchf.dto.req.XN631710Req;
import com.cdkj.gchf.dto.req.XN631712Req;
import com.cdkj.gchf.dto.req.XN631713Req;
import com.cdkj.gchf.dto.req.XN631918Req;
import com.cdkj.gchf.dto.req.XN631919Req;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.exception.BizException;

@Service
public class WorkerAttendanceAOImpl implements IWorkerAttendanceAO {

    @Autowired
    private IWorkerAttendanceBO workerAttendanceBO;

    @Autowired
    private IProjectConfigBO projectConfigBO;

    @Autowired
    private ITeamMasterBO teamMasterBO;

    @Autowired
    private IProjectWorkerBO projectWorkerBO;

    @Override
    public String addWorkerAttendance(XN631710Req data) {
        TeamMaster teamMaster = teamMasterBO.getTeamMaster(data.getTeamSysNo());
        if (teamMaster == null) {
            throw new BizException("XN631710", "班组信息不存在");
        }
        ProjectWorker projectWorker = projectWorkerBO
            .getProjectWorker(data.getProjectWorkerCode());
        if (projectWorker == null) {
            throw new BizException("XN631710", "项目人员不存在");
        }
        return workerAttendanceBO.saveWorkerAttendance(data);
    }

    @Override
    public void editWorkerAttendance(XN631712Req data) {
        if (workerAttendanceBO.getWorkerAttendance(data.getCode())
            .getUploadStatus()
            .equals(EUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
            throw new BizException("XN631712", "人员考勤已上传,无法修改");
        }
        workerAttendanceBO.refreshWorkerAttendance(data);
    }

    @Override
    public int dropWorkerAttendance(String code) {
        WorkerAttendance workerAttendance = workerAttendanceBO
            .getWorkerAttendance(code);
        if (workerAttendance.getUploadStatus()
            .equals(EUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
            throw new BizException("XN631711", "人员考勤已上传，不可删除");
        }
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
        for (String code : codeList) {
            WorkerAttendance workerAttendance = workerAttendanceBO
                .getWorkerAttendance(code);
            ProjectConfig projectConfigByLocal = projectConfigBO
                .getProjectConfigByLocal(workerAttendance.getProjectCode());
            if (projectConfigByLocal == null) {
                throw new BizException("XN631714", "该项目未配置，无法查询");
            }
        }
        workerAttendanceBO.saveWorkerAttendanceToPlantform(userId, codeList);
    }

    @Override
    public void importWorkerAttendanceList(XN631713Req req) {
    }

}
