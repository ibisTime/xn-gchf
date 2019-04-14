package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IWorkerInfoDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.WorkerInfo;

@Repository("workerInfoDAOImpl")
public class WorkerInfoDAOImpl extends AMybatisTemplate
        implements IWorkerInfoDAO {

    @Override
    public int insert(WorkerInfo data) {
        return super.insert(NAMESPACE.concat("insert_WorkerInfo"), data);
    }

    @Override
    public int delete(WorkerInfo data) {
        return super.delete(NAMESPACE.concat("delete_WorkerInfo"), data);
    }

    @Override
    public int updateWorkerInfo(WorkerInfo condition) {
        return super.update(NAMESPACE.concat("update_WorkerInfo"), condition);
    }

    @Override
    public int updateWorkerInfoAboutIdcard(WorkerInfo condition) {
        return super.update(NAMESPACE.concat("update_WorkerInfoAboutIdcard"),
            condition);
    }

    @Override
    public WorkerInfo select(WorkerInfo condition) {
        return super.select(NAMESPACE.concat("select_WorkerInfo"), condition,
            WorkerInfo.class);
    }

    @Override
    public long selectTotalCount(WorkerInfo condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_WorkerInfo_count"), condition);
    }

    @Override
    public List<WorkerInfo> selectList(WorkerInfo condition) {
        return super.selectList(NAMESPACE.concat("select_WorkerInfo"),
            condition, WorkerInfo.class);
    }

    @Override
    public List<WorkerInfo> selectList(WorkerInfo condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_WorkerInfo"), start,
            count, condition, WorkerInfo.class);
    }

    @Override
    public int updateWorkerInfoAboutPhone(WorkerInfo condition) {
        return super.update(NAMESPACE.concat("update_WorkerInfoAboutPhone"),
            condition);
    }

    @Override
    public WorkerInfo selectBriefWorkerInfo(WorkerInfo condition) {
        return super.select(NAMESPACE.concat("select_BrifeWorkerInfo"),
            condition, WorkerInfo.class);
    }

}
