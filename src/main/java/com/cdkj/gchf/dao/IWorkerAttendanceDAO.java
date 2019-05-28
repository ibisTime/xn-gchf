package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.WorkerAttendance;
import org.apache.ibatis.annotations.Param;

public interface IWorkerAttendanceDAO extends IBaseDAO<WorkerAttendance> {

    String NAMESPACE = IWorkerAttendanceDAO.class.getName().concat(".");

    void deleteWorkerAttendance(WorkerAttendance workerAttendance);

    void deleteWorkerAttendanceByWorkerCode(WorkerAttendance workerAttendance);

    /**
     * 批量删除考勤
     *
     * @param codes 主键列表
     */
    void batchDeleteWorkerAttendacne(WorkerAttendance workerAttendance);

    int update(WorkerAttendance workerAttendance);

    int updateStatus(WorkerAttendance workerAttendance);

    int updateWorkerAttendanceDeleteStatus(WorkerAttendance workerAttendance);

    int updateWorkerAttendanceTeamName(WorkerAttendance workerAttendance);

    int selectWorkerAttendance30Day(String userId);

    /**
     * 查询今日出工人数
     */
    int selectWorkerAttendanceToday(String userId);
}
