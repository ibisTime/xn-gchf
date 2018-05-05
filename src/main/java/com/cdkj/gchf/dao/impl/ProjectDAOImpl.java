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

    @Override
    public int update(Project data) {
        return super.update(NAMESPACE.concat("update_project"), data);
    }

    @Override
    public int approveProject(Project data) {
        return super.update(NAMESPACE.concat("approve_project"), data);
    }

    @Override
    public void toApprove(Project data) {
        super.update(NAMESPACE.concat("to_aprrove"), data);
    }

    @Override
    public void projectEnd(Project data) {
        super.update(NAMESPACE.concat("project_end"), data);
    }

    @Override
    public void toBuilding(Project data) {
        super.update(NAMESPACE.concat("to_building"), data);
    }

    @Override
    public void stopProject(Project data) {
        super.update(NAMESPACE.concat("stop_project"), data);
    }

    @Override
    public void restartProject(Project data) {
        super.update(NAMESPACE.concat("restart_project"), data);
    }

}
