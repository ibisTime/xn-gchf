package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.WorkerContract;
import com.cdkj.gchf.dto.req.XN631916Req;
import com.cdkj.gchf.dto.req.XN631917Req;

public interface IWorkerContractBO extends IPaginableBO<WorkerContract> {

    public String saveWorkerContract(WorkerContract data);

    public int removeWorkerContract(String code);

    public int refreshWorkerContract(WorkerContract data);

    public List<WorkerContract> queryWorkerContractList(
            WorkerContract condition);

    public WorkerContract getWorkerContract(String code);

    /****国家平台接口****/
    public void doUpload(XN631916Req req, ProjectConfig projectConfig);

    public void doQuery(XN631917Req req, ProjectConfig projectConfig);
}
