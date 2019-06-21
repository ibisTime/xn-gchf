package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.WorkerEntryExitRecord;

public interface IWorkerEntryExitRecordDAO
        extends IBaseDAO<WorkerEntryExitRecord> {
    String NAMESPACE = IWorkerEntryExitRecordDAO.class.getName().concat(".");

    WorkerEntryExitRecord selectMorningRecoder(
            WorkerEntryExitRecord workerEntryExitRecord);

    WorkerEntryExitRecord selectAfternoonRecoder(
            WorkerEntryExitRecord workerEntryExitRecord);

    /**
     * @return 根据userId查询当天项目进出人数
     */
    Long selectProjectRecordCount(String userId);
}
