package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.WorkerContract;

import java.util.List;

public interface IWorkerContractDAO extends IBaseDAO<WorkerContract> {
    String NAMESPACE = IWorkerContractDAO.class.getName().concat(".");

    void batchInsert(List<WorkerContract> workerContractList);
    int update(WorkerContract workerContract);

    int updateWorkerContractStatus(WorkerContract workerContract);

    int updateWorkerContractDeleteStatus(WorkerContract workerContract);

}
