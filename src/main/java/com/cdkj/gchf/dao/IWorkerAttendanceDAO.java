package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.WorkerAttendance;

public interface IWorkerAttendanceDAO extends IBaseDAO<WorkerAttendance> {
    String NAMESPACE = IWorkerAttendanceDAO.class.getName().concat(".");

    int update(WorkerAttendance workerAttendance);

}
