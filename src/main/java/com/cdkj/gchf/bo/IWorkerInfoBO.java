package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.api.impl.XN631693ReqData;
import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.WorkerInfo;
import com.cdkj.gchf.dto.req.XN631790Req;
import com.cdkj.gchf.dto.req.XN631791Req;
import com.cdkj.gchf.dto.req.XN631792Req;
import com.cdkj.gchf.dto.req.XN631793Req;

public interface IWorkerInfoBO extends IPaginableBO<WorkerInfo> {

    public boolean isWorkerInfoExist(String code);

    public String saveWorkerInfo(XN631790Req req);

    public String saveWorkerInfo(WorkerInfo workerInfo);

    String saveWorkerInfoByImport(XN631693ReqData data);

    public int removeWorkerInfo(String code);

    public int refreshWorkerInfo(XN631791Req req);

    public int refreshWorkerInfo(XN631792Req req);

    public int refreshWorkerInfo(XN631793Req req);

    void refreshAttendancePic(String code, String attendancePicture);

    public WorkerInfo getWorkerInfoByCelephone(String phone);

    public WorkerInfo getWorkerInfo(String code);

    public WorkerInfo getBriefWorkerInfo(String code);

    public WorkerInfo getWorkerInfoByCondition(WorkerInfo workerInfo);

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
