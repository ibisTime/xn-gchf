package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IProjectConfigDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.ProjectConfig;

@Repository("projectConfigDAOImpl")
public class ProjectConfigDAOImpl extends AMybatisTemplate
        implements IProjectConfigDAO {

    @Override
    public int insert(ProjectConfig data) {
        return super.insert(NAMESPACE.concat("insert_projectConfig"), data);
    }

    @Override
    public int delete(ProjectConfig data) {
        return super.delete(NAMESPACE.concat("delete_projectConfig"), data);
    }

    @Override
    public ProjectConfig select(ProjectConfig condition) {
        return super.select(NAMESPACE.concat("select_projectConfig"), condition,
            ProjectConfig.class);
    }

    @Override
    public long selectTotalCount(ProjectConfig condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_projectConfig_count"), condition);
    }

    @Override
    public List<ProjectConfig> selectList(ProjectConfig condition) {
        return super.selectList(NAMESPACE.concat("select_projectConfig"),
            condition, ProjectConfig.class);
    }

    @Override
    public List<ProjectConfig> selectList(ProjectConfig condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_projectConfig"), start,
            count, condition, ProjectConfig.class);
    }

    @Override
    public int update(ProjectConfig projectConfig) {
        return super.update(NAMESPACE.concat("update_projectConfig"),
            projectConfig);
    }

}
