package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.WorkerAttendance;
import com.cdkj.gchf.dto.req.XN631918Req;
import com.cdkj.gchf.dto.req.XN631919Req;

@Component
public interface IWorkerAttendanceAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addWorkerAttendance(WorkerAttendance data);

    public int dropWorkerAttendance(String code);

    public int editWorkerAttendance(WorkerAttendance data);

    public Paginable<WorkerAttendance> queryWorkerAttendancePage(int start,
            int limit, WorkerAttendance condition);

    public List<WorkerAttendance> queryWorkerAttendanceList(
            WorkerAttendance condition);

    public WorkerAttendance getWorkerAttendance(String code);

    /****国家平台接口****/
    public void uploadWorkerAttendance(XN631918Req data);

    public Paginable<WorkerAttendance> queryWorkerAttendance(XN631919Req req);
}