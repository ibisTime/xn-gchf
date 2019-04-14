package com.cdkj.gchf.ao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.WorkerAttendance;
import com.cdkj.gchf.dto.req.XN631710Req;
import com.cdkj.gchf.dto.req.XN631712Req;
import com.cdkj.gchf.dto.req.XN631713Req;
import com.cdkj.gchf.dto.req.XN631918Req;
import com.cdkj.gchf.dto.req.XN631919Req;

@Component
public interface IWorkerAttendanceAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addWorkerAttendance(XN631710Req data);

    public int dropWorkerAttendance(String code);

    public void editWorkerAttendance(XN631712Req data);

    public void batchCreateAttandance(String projectCode, String direction,
            Date startDatetime, Date endDatetime);

    public Paginable<WorkerAttendance> queryWorkerAttendancePage(int start,
            int limit, WorkerAttendance condition);

    public List<WorkerAttendance> queryWorkerAttendanceList(
            WorkerAttendance condition);

    public WorkerAttendance getWorkerAttendance(String code);

    public void importWorkerAttendanceList(XN631713Req req);

    public void uploadWorkerAttendanceList(String userId,
            List<String> codeList);

    /****国家平台接口****/
    public void uploadWorkerAttendance(XN631918Req data);

    public Paginable<WorkerAttendance> queryWorkerAttendance(XN631919Req req);
}
