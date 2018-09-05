package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IProjectDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.Project;

@Repository("projectDAOImpl")
public class ProjectDAOImpl extends AMybatisTemplate implements IProjectDAO {

    @Override
    public int insert(Project data) {
        return super.insert(NAMESPACE.concat("insert_project"), data);
    }

    @Override
    public int delete(Project data) {
        return 0;
    }

    @Override
    public int updateEditProject(Project data) {
        return super.update(NAMESPACE.concat("update_editProject"), data);
    }

    @Override
    public int updateStartProject(Project data) {
        return super.update(NAMESPACE.concat("update_startProject"), data);
    }

    @Override
    public void updatePauseProject(Project data) {
        super.update(NAMESPACE.concat("update_pauseProject"), data);
    }

    @Override
    public void updateRestartProject(Project data) {
        super.update(NAMESPACE.concat("update_restartProject"), data);
    }

    @Override
    public void updateEndProject(Project data) {
        super.update(NAMESPACE.concat("update_endProject"), data);
    }

    @Override
    public int updateSalaryDelayDays(Project data) {
        return super.update(NAMESPACE.concat("update_salaryDelayDays"), data);
    }

    @Override
    public Project select(Project condition) {
        return super.select(NAMESPACE.concat("select_project"), condition,
            Project.class);
    }

    @Override
    public long selectTotalCount(Project condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_project_count"),
            condition);
    }

    @Override
    public List<Project> selectList(Project condition) {
        return super.selectList(NAMESPACE.concat("select_project"), condition,
            Project.class);
    }

    @Override
    public List<Project> selectList(Project condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_project"), start,
            count, condition, Project.class);
    }

}
