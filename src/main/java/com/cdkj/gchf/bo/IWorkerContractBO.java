package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.WorkerContract;
import com.cdkj.gchf.dto.req.XN631670Req;
import com.cdkj.gchf.dto.req.XN631672Req;
import com.cdkj.gchf.dto.req.XN631673ReqData;
import com.cdkj.gchf.dto.req.XN631916Req;
import com.cdkj.gchf.dto.req.XN631917Req;
import com.google.gson.JsonObject;

public interface IWorkerContractBO extends IPaginableBO<WorkerContract> {

    public String saveWorkerContract(WorkerContract req);

    public String saveWorkerContract(XN631670Req req);

    public String saveWorkerContract(XN631673ReqData data,
            ProjectWorker projectWorker);

    public void removeWorkerContract(String userId, String code);

    public void refreshWorkerContract(XN631672Req data);

    void updateWorkerContractDeleteStatus(String code, String status);

    WorkerContract getWorkerContract(String projectCode, String workerCode);

    void fakeDeleteWorkerContract(String workerCode);

    void fakeDeleteWorkerContractByProjectCode(String projectCode);

    public List<WorkerContract> queryWorkerContractList(
            WorkerContract condition);

    List<WorkerContract> queryWorkerContract(String projectCode,
            String workerCode);

    public WorkerContract getWorkerContract(String code);

    public void refreshUploadStatus(String code, String status);

    JsonObject getRequestJson(WorkerContract workerContract,
            ProjectConfig projectConfig);

    /****国家平台接口****/
    public void doUpload(XN631916Req req, ProjectConfig projectConfig);

    public Paginable<WorkerContract> doQuery(XN631917Req req,
            ProjectConfig projectConfig);
}
