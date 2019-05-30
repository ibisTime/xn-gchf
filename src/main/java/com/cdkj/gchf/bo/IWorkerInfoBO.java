package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.dto.req.XN631693ReqData;
import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.WorkerInfo;
import com.cdkj.gchf.dto.req.XN631790Req;
import com.cdkj.gchf.dto.req.XN631791Req;
import com.cdkj.gchf.dto.req.XN631792Req;
import com.cdkj.gchf.dto.req.XN631793Req;
import com.cdkj.gchf.dto.req.XN631795Req;
import com.cdkj.gchf.dto.req.XN631797Req;
import com.cdkj.gchf.zqzn.ZqznInfoBack;
import com.cdkj.gchf.zqzn.ZqznInfoFront;

public interface IWorkerInfoBO extends IPaginableBO<WorkerInfo> {

    String saveWorkerInfo(XN631790Req req);

    /**
     * @param workerInfo
     * @return
     */
    String saveWorkerInfo(WorkerInfo workerInfo);

    String saveWorkerInfo(XN631795Req req, ZqznInfoFront front,
            ZqznInfoBack back);

    String saveWorkerInfoByImport(XN631693ReqData data);

    String refreshWorkerInfo(XN631797Req req);

    int refreshWorkerInfo(XN631791Req req);

    int refreshWorkerInfo(XN631792Req req);

    int refreshWorkerInfo(XN631793Req req);

    /**
     * @param handIdCardImage 更新手持身份证照
     */
    void refreshHandIdCardImage(String code, String handIdCardImage);

    void refreshAttendancePic(String code, String attendancePicture,
            String workerUploadStatus, String attendancePicUploadStatus);

    public WorkerInfo getWorkerInfo(String code);

    public WorkerInfo getBriefWorkerInfo(String code);

    public WorkerInfo getWorkerInfoByIdCardNumber(String idCardNumber);

    public List<WorkerInfo> queryWorkerInfoList(WorkerInfo condition);

    void updateWorkerInfoAttendance(String code, String workerGuid,
            String picGuid);

    public List<WorkerInfo> queryStaffListBrief(WorkerInfo condition, int start,
            int count);

    public long queryTotalCount(WorkerInfo condition);

    /****国家平台接口****/
    public Paginable<WorkerInfo> doQuery(String idCardNumber,
            ProjectConfig projectConfig);
}
