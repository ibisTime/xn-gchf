package com.cdkj.gchf.dao.impl;

import java.util.List;
import java.util.Map;

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
        return super.delete(NAMESPACE.concat("delete_project"), data);
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
    public void update(Project project) {
        super.update(NAMESPACE.concat("update_project"), project);
    }

    @Override
    public void updateSecretStatus(Project project) {
        super.update(NAMESPACE.concat("update_projectSecretStatus"), project);
    }

    @Override
    public void updateContractorCorp(Project project) {
        super.update(NAMESPACE.concat("update_contractorCorp"), project);
    }

    @Override
    public void updateUsedOcrCount(Project project) {
        super.update(NAMESPACE.concat("update_usedOcrCount"), project);
    }

    @Override
    public List<Map> queryProjectInfo_led(String userId) {
        return super.selectList(NAMESPACE.concat("selectProjectInfo"), userId,
                Map.class);
    }
}
