package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IWorkerAttendanceAO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.IWorkerAttendanceBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.WorkerAttendance;
import com.cdkj.gchf.dto.req.XN631918Req;
import com.cdkj.gchf.dto.req.XN631919Req;
import com.cdkj.gchf.exception.BizException;

@Service
public class WorkerAttendanceAOImpl implements IWorkerAttendanceAO {

    @Autowired
    IWorkerAttendanceBO workerAttendanceBO;

    @Autowired
    private IProjectConfigBO projectConfigBO;

    @Override
    public String addWorkerAttendance(WorkerAttendance data) {
        return workerAttendanceBO.saveWorkerAttendance(data);
    }

    @Override
    public int editWorkerAttendance(WorkerAttendance data) {
        return workerAttendanceBO.refreshWorkerAttendance(data);
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

        workerAttendanceBO.doQuery(req, projectConfig);
        return null;
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

}
