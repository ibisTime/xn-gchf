package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IWorkerEntryExitRecordDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.WorkerEntryExitRecord;

@Repository("workerEntryExitRecordDAOImpl")
public class WorkerEntryExitRecordDAOImpl extends AMybatisTemplate
        implements IWorkerEntryExitRecordDAO {

    @Override
    public int insert(WorkerEntryExitRecord data) {
        return super.insert(NAMESPACE.concat("insert_workerEntryExitRecord"),
                data);
    }

    @Override
    public int delete(WorkerEntryExitRecord data) {
        return super.delete(NAMESPACE.concat("delete_workerEntryExitRecord"),
                data);
    }

    @Override
    public WorkerEntryExitRecord select(WorkerEntryExitRecord condition) {
        return super.select(NAMESPACE.concat("select_workerEntryExitRecord"),
                condition, WorkerEntryExitRecord.class);
    }

    @Override
    public long selectTotalCount(WorkerEntryExitRecord condition) {
        return super.selectTotalCount(
                NAMESPACE.concat("select_workerEntryExitRecord_count"), condition);
    }

    @Override
    public List<WorkerEntryExitRecord> selectList(
            WorkerEntryExitRecord condition) {
        return super.selectList(
                NAMESPACE.concat("select_workerEntryExitRecord"), condition,
                WorkerEntryExitRecord.class);
    }

    @Override
    public List<WorkerEntryExitRecord> selectList(
            WorkerEntryExitRecord condition, int start, int count) {
        return super.selectList(
                NAMESPACE.concat("select_workerEntryExitRecord"), start, count,
                condition, WorkerEntryExitRecord.class);
    }

    @Override
    public WorkerEntryExitRecord selectMorningRecoder(
            WorkerEntryExitRecord workerEntryExitRecord) {
        return super.select(NAMESPACE.concat("select_morning_recorder"),
                workerEntryExitRecord, WorkerEntryExitRecord.class);
    }

    @Override
    public WorkerEntryExitRecord selectAfternoonRecoder(
            WorkerEntryExitRecord workerEntryExitRecord) {
        return super.select(NAMESPACE.concat("select_afternoon_recorder"),
                workerEntryExitRecord, WorkerEntryExitRecord.class);
    }

    @Override
    public Long selectProjectRecordCount(String userId) {
        return super.select(NAMESPACE.concat("selectProjectRecordCount"),
                userId, Long.class);
    }
}
