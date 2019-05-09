package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.WorkerEntryExitRecord;

public interface IWorkerEntryExitRecordDAO
        extends IBaseDAO<WorkerEntryExitRecord> {
    String NAMESPACE = IWorkerEntryExitRecordDAO.class.getName().concat(".");
}
