package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.WorkerContract;
import com.cdkj.gchf.dto.req.XN631916Req;
import com.cdkj.gchf.dto.req.XN631917Req;

@Component
public interface IWorkerContractAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addWorkerContract(WorkerContract data);

    public int dropWorkerContract(String code);

    public int editWorkerContract(WorkerContract data);

    public Paginable<WorkerContract> queryWorkerContractPage(int start,
            int limit, WorkerContract condition);

    public List<WorkerContract> queryWorkerContractList(
            WorkerContract condition);

    public WorkerContract getWorkerContract(String code);

    /****国家平台接口****/
    public void uploadWorkerContract(XN631916Req req);

    public Paginable<WorkerContract> queryWorkerContract(XN631917Req req);

}