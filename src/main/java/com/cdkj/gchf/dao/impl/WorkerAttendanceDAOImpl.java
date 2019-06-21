package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IWorkerAttendanceDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.WorkerAttendance;

@Repository("workerAttendanceDAOImpl")
public class WorkerAttendanceDAOImpl extends AMybatisTemplate
        implements IWorkerAttendanceDAO {

    @Override
    public int insert(WorkerAttendance data) {
        return super.insert(NAMESPACE.concat("insert_workerAttendance"), data);
    }

    @Override
    public int delete(WorkerAttendance data) {
        return super.delete(NAMESPACE.concat("delete_workerAttendance"), data);
    }

    @Override
    public WorkerAttendance select(WorkerAttendance condition) {
        return super.select(NAMESPACE.concat("select_workerAttendance"),
            condition, WorkerAttendance.class);
    }

    @Override
    public long selectTotalCount(WorkerAttendance condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_workerAttendance_count"), condition);
    }

    @Override
    public List<WorkerAttendance> selectList(WorkerAttendance condition) {
        return super.selectList(NAMESPACE.concat("select_workerAttendance"),
            condition, WorkerAttendance.class);
    }

    @Override
    public List<WorkerAttendance> selectList(WorkerAttendance condition,
            int start, int count) {
        return super.selectList(NAMESPACE.concat("select_workerAttendance"),
            start, count, condition, WorkerAttendance.class);
    }

    @Override
    public void deleteWorkerAttendance(WorkerAttendance workerAttendance) {
        super.delete(NAMESPACE.concat("delete_workerAttendance"),
                workerAttendance);
    }

    @Override
    public void deleteWorkerAttendanceByWorkerCode(
            WorkerAttendance workerAttendance) {
        super.delete(NAMESPACE.concat("deleteWorkerAttendanceByWorkerCode"),
                workerAttendance);
    }

    @Override
    public void batchDeleteWorkerAttendacne(WorkerAttendance workerAttendance) {
        super.delete(NAMESPACE.concat("batch_delete_workerAttendance"),
                workerAttendance);
    }

    @Override
    public int update(WorkerAttendance workerAttendance) {
        return super.update(NAMESPACE.concat("update_workerAttendance"),
            workerAttendance);
    }

    @Override
    public int updateStatus(WorkerAttendance workerAttendance) {
        return super.update(NAMESPACE.concat("update_workerAttendance_status"),
            workerAttendance);
    }

    @Override
    public int updateWorkerAttendanceDeleteStatus(
            WorkerAttendance workerAttendance) {
        return super.update(
            NAMESPACE.concat("update_workerAttendance_delete_status"),
            workerAttendance);
    }

    @Override
    public int updateWorkerAttendanceTeamName(
            WorkerAttendance workerAttendance) {
        return super.update(
                NAMESPACE.concat("update_workerAttendance_team_name"),
                workerAttendance);
    }

    @Override
    public int selectWorkerAttendance30Day(String userId) {
        return super.select(NAMESPACE.concat("selectWorkerAttendance30Day"),
                userId, Integer.class);
    }

    @Override
    public int selectWorkerAttendanceToday(String userId) {
        return super.select(NAMESPACE.concat("selectWorkerAttendanceToday"),
                userId, Integer.class);
    }

    @Override
    public WorkerAttendance selectWorkerNewlyWorkerAttendanceData(String workerCode) {
        return super.select(NAMESPACE.concat("selectWorkerNewlyWorkerAttendanceData"),
                workerCode, WorkerAttendance.class);
    }
}
