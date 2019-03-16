package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.WorkerContract;

public interface IWorkerContractDAO extends IBaseDAO<WorkerContract> {
    String NAMESPACE = IWorkerContractDAO.class.getName().concat(".");

    int update(WorkerContract workerContract);

}
