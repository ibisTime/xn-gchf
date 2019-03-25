package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IProjectWorkerDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.ProjectWorker;

@Repository("projectWorkerDAOImpl")
public class ProjectWorkerDAOImpl extends AMybatisTemplate
        implements IProjectWorkerDAO {

    @Override
    public int insert(ProjectWorker data) {
        return super.insert(NAMESPACE.concat("insert_projectWorker"), data);
    }

    @Override
    public int delete(ProjectWorker data) {
        return super.delete(NAMESPACE.concat("delete_projectWorker"), data);
    }

    @Override
    public ProjectWorker select(ProjectWorker condition) {
        return super.select(NAMESPACE.concat("select_projectWorker"), condition,
            ProjectWorker.class);
    }

    @Override
    public long selectTotalCount(ProjectWorker condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_projectWorker_count"), condition);
    }

    @Override
    public List<ProjectWorker> selectList(ProjectWorker condition) {
        return super.selectList(NAMESPACE.concat("select_projectWorker"),
            condition, ProjectWorker.class);
    }

    @Override
    public List<ProjectWorker> selectList(ProjectWorker condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_projectWorker"), start,
            count, condition, ProjectWorker.class);
    }

    @Override
    public int update(ProjectWorker projectWorker) {
        return super.update(NAMESPACE.concat("update_projectWorker"),
            projectWorker);
    }

    @Override
    public int updateTeamSysNoByLocal(ProjectWorker projectWorker) {
        return super.update(NAMESPACE.concat("update_teamSysNoByLocal"),
            projectWorker);
    }

}
