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

    /**
     * 新增 
     */
    public String addWorkerAttendance(XN631710Req data);

    /**
     * 删除 
     */
    public void dropWorkerAttendance(List<String> codeList);

    /**
     * 修改
     */
    public void editWorkerAttendance(XN631712Req data);

    /**
     * 批量生成考勤
     */
    public void batchCreateAttandance(String projectCode, String teamMasterNo,
            String direction, Date startDatetime, Date endDatetime);

    /**
     * 导入人员考勤 
     */
    public void importWorkerAttendanceList(XN631713Req req);

    /**
     * 上传人员考勤 
     */
    public void uploadWorkerAttendanceList(String userId,
            List<String> codeList);

    /**
     *分页查 
     */
    public Paginable<WorkerAttendance> queryWorkerAttendancePage(int start,
            int limit, WorkerAttendance condition);

    public List<WorkerAttendance> queryWorkerAttendanceList(
            WorkerAttendance condition);

    /**
     * 根据code查 
     */
    public WorkerAttendance getWorkerAttendance(String code);

    /****国家平台接口****/
    public void uploadWorkerAttendance(XN631918Req data);

    public Paginable<WorkerAttendance> queryWorkerAttendance(XN631919Req req);
}
