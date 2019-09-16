package com.cdkj.gchf.ao;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.WorkerAttendance;
import com.cdkj.gchf.dto.req.XN631710Req;
import com.cdkj.gchf.dto.req.XN631712Req;
import com.cdkj.gchf.dto.req.XN631713Req;
import com.cdkj.gchf.dto.req.XN631918Req;
import com.cdkj.gchf.dto.req.XN631919Req;
import java.util.Date;
import java.util.List;

/**
 * @author old3
 */
public interface IWorkerAttendanceAO {
    String DEFAULT_ORDER_COLUMN = "code";

    /**
     * 新增 
     */
    String addWorkerAttendance(XN631710Req data);


    /**
     * 根据传来的考勤主键列表 批量删除考勤
     * @param codeList 主键列表
     */
    void dropWorkerAttendance(List<String> codeList);

    /**
     * 修改
     */
    void editWorkerAttendance(XN631712Req data);

    /**
     * 批量生成考勤
     */
    void batchCreateAttandance(String projectCode, String teamMasterNo,
                               String direction, Date startDatetime, Date endDatetime);

    /**
     * 导入人员考勤 
     */
    void importWorkerAttendanceList(XN631713Req req);

    /**
     * 上传人员考勤 
     */
    void uploadWorkerAttendanceList(String userId,
                                    List<String> codeList);

    /**
     *分页查 
     */
    Paginable<WorkerAttendance> queryWorkerAttendancePage(int start,
                                                          int limit, WorkerAttendance condition);

    List<WorkerAttendance> queryWorkerAttendanceList(
            WorkerAttendance condition);

    /**
     * 根据code查 
     */
    WorkerAttendance getWorkerAttendance(String code);

    /****国家平台接口****/
    void uploadWorkerAttendance(XN631918Req data);

    Paginable<WorkerAttendance> queryWorkerAttendance(XN631919Req req);
}
