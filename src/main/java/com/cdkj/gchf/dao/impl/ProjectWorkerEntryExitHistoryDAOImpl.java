package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IProjectWorkerEntryExitHistoryDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.ProjectWorkerEntryExitHistory;

@Repository("projectWorkerEntryExitHistoryDAOImpl")
public class ProjectWorkerEntryExitHistoryDAOImpl extends AMybatisTemplate
        implements IProjectWorkerEntryExitHistoryDAO {

    @Override
    public int insert(ProjectWorkerEntryExitHistory data) {
        return super.insert(
            NAMESPACE.concat("insert_projectWorkerEntryExitHistory"), data);
    }

    @Override
    public int delete(ProjectWorkerEntryExitHistory data) {
        return super.delete(
            NAMESPACE.concat("delete_projectWorkerEntryExitHistory"), data);
    }

    @Override
    public ProjectWorkerEntryExitHistory select(
            ProjectWorkerEntryExitHistory condition) {
        return super.select(
            NAMESPACE.concat("select_projectWorkerEntryExitHistory"), condition,
            ProjectWorkerEntryExitHistory.class);
    }

    @Override
    public long selectTotalCount(ProjectWorkerEntryExitHistory condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_projectWorkerEntryExitHistory_count"),
            condition);
    }

    @Override
    public List<ProjectWorkerEntryExitHistory> selectList(
            ProjectWorkerEntryExitHistory condition) {
        return super.selectList(
            NAMESPACE.concat("select_projectWorkerEntryExitHistory"), condition,
            ProjectWorkerEntryExitHistory.class);
    }

    @Override
    public List<ProjectWorkerEntryExitHistory> selectList(
            ProjectWorkerEntryExitHistory condition, int start, int count) {
        return super.selectList(
            NAMESPACE.concat("select_projectWorkerEntryExitHistory"), start,
            count, condition, ProjectWorkerEntryExitHistory.class);
    }

    @Override
    public int update(
            ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory) {
        return super.update("update_projectWorkerEntryExitHistory",
            projectWorkerEntryExitHistory);
    }

    @Override
    public int updateProjectWorkerEntryHistoryStatus(
            ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory) {
        return super.update("update_projectWorkerEntryExitHistory_status",
            projectWorkerEntryExitHistory);
    }

    @Override
    public int updateProjectWorkerEntryHistoryDeleteStatus(
            ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory) {
        return super.update("update_projectWorker_delete_status",
            projectWorkerEntryExitHistory);
    }

}
