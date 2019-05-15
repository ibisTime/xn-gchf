package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.WorkerAttendance;

import java.util.List;

public interface IWorkerAttendanceDAO extends IBaseDAO<WorkerAttendance> {
    String NAMESPACE = IWorkerAttendanceDAO.class.getName().concat(".");

    void deleteWorkerAttendance(WorkerAttendance workerAttendance);

    /**
     * 批量删除考勤
     *
     * @param codes 主键列表
     */
    void batchDeleteWorkerAttendacne(List<String> codes);

    int update(WorkerAttendance workerAttendance);

    int updateStatus(WorkerAttendance workerAttendance);

    int updateWorkerAttendanceDeleteStatus(WorkerAttendance workerAttendance);

    int updateWorkerAttendanceTeamName(WorkerAttendance workerAttendance);
}
