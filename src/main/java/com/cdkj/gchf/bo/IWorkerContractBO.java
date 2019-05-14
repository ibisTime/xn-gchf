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
    /**
     * 新增劳动合同 
     */
    public String saveWorkerContract(XN631670Req req);

    /**
     * 新增劳动合同 
     */
    public String saveWorkerContract(XN631673ReqData data,
            ProjectWorker projectWorker);

    /**
     * 根据workercode假删
     */
    void fakeDeleteWorkerContract(String workerCode);

    /**
     *根据项目编号假删 
     */
    void fakeDeleteWorkerContractByProjectCode(String projectCode);


    /**
     * 修改劳动合同
     */
    public void refreshWorkerContract(XN631672Req data);

    /**
     * 修改上传状态
     */
    public void refreshUploadStatus(String code, String status);

    /**
     * 修改删除状态 
     */
    void updateWorkerContractDeleteStatus(String code, String status);

    /**
     * 查询项目人员劳动合同 
     */
    WorkerContract getWorkerContract(String projectCode, String workerCode);

    public List<WorkerContract> queryWorkerContractList(
            WorkerContract condition);


    public WorkerContract getWorkerContract(String code);

    /**
     * 获取上传json 
     */
    JsonObject getRequestJson(WorkerContract workerContract,
            ProjectConfig projectConfig);

    /****国家平台接口****/
    public void doUpload(XN631916Req req, ProjectConfig projectConfig);

    public Paginable<WorkerContract> doQuery(XN631917Req req,
            ProjectConfig projectConfig);
}
