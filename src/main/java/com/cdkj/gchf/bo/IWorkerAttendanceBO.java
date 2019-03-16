package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.WorkerAttendance;
import com.cdkj.gchf.dto.req.XN631918Req;
import com.cdkj.gchf.dto.req.XN631919Req;

public interface IWorkerAttendanceBO extends IPaginableBO<WorkerAttendance> {

    public String saveWorkerAttendance(WorkerAttendance data);

    public int removeWorkerAttendance(String code);

    public int refreshWorkerAttendance(WorkerAttendance data);

    public List<WorkerAttendance> queryWorkerAttendanceList(
            WorkerAttendance condition);

    public WorkerAttendance getWorkerAttendance(String code);

    /****国家平台接口****/
    public void doUpload(XN631918Req req, ProjectConfig projectConfig);

    public void doQuery(XN631919Req req, ProjectConfig projectConfig);
}
