package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.domain.WorkerAttendance;
import com.cdkj.gchf.dto.req.XN631710Req;
import com.cdkj.gchf.dto.req.XN631712Req;
import com.cdkj.gchf.dto.req.XN631918Req;
import com.cdkj.gchf.dto.req.XN631919Req;
import com.google.gson.JsonObject;

public interface IWorkerAttendanceBO extends IPaginableBO<WorkerAttendance> {

    public String saveWorkerAttendance(XN631710Req data);

    public String saveWorkerAttendance(WorkerAttendance workerAttendance);

    public int removeWorkerAttendance(String code);

    public void refreshWorkerAttendance(XN631712Req data);

    int updateWorkerAttendanceDeleteStatus(String code, String status);

    public List<WorkerAttendance> queryWorkerAttendanceList(
            WorkerAttendance condition);

    public WorkerAttendance getWorkerAttendance(String code);

    public JsonObject getRequestJson(TeamMaster teamMaster,
            WorkerAttendance workerAttendance,
            ProjectConfig projectConfigByLocal);

    public void saveWorkerAttendanceToPlantform(String userId,
            List<String> codeList);

    /****国家平台接口****/
    public void doUpload(XN631918Req req, ProjectConfig projectConfig);

    public Paginable<WorkerAttendance> doQuery(XN631919Req req,
            ProjectConfig projectConfig);
}
