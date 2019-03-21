package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IWorkerContractDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.WorkerContract;

@Repository("workerContractDAOImpl")
public class WorkerContractDAOImpl extends AMybatisTemplate
        implements IWorkerContractDAO {

    @Override
    public int insert(WorkerContract data) {
        return super.insert(NAMESPACE.concat("insert_workerContract"), data);
    }

    @Override
    public int delete(WorkerContract data) {
        return super.delete(NAMESPACE.concat("delete_workerContract"), data);
    }

    @Override
    public WorkerContract select(WorkerContract condition) {
        return super.select(NAMESPACE.concat("select_workerContract"),
            condition, WorkerContract.class);
    }

    @Override
    public long selectTotalCount(WorkerContract condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_workerContract_count"), condition);
    }

    @Override
    public List<WorkerContract> selectList(WorkerContract condition) {
        return super.selectList(NAMESPACE.concat("select_workerContract"),
            condition, WorkerContract.class);
    }

    @Override
    public List<WorkerContract> selectList(WorkerContract condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_workerContract"),
            start, count, condition, WorkerContract.class);
    }

    @Override
    public int update(WorkerContract workerContract) {
        return super.update(NAMESPACE.concat("update_workerContract"),
            workerContract);
    }

}
