package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IProjectCardDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.ProjectCard;

@Repository("projectCardDAOImpl")
public class ProjectCardDAOImpl extends AMybatisTemplate
        implements IProjectCardDAO {

    @Override
    public int insert(ProjectCard data) {
        return super.insert(NAMESPACE.concat("insert_projectCard"), data);
    }

    @Override
    public int delete(ProjectCard data) {
        return super.delete(NAMESPACE.concat("delete_projectCard"), data);
    }

    @Override
    public ProjectCard select(ProjectCard condition) {
        return super.select(NAMESPACE.concat("select_projectCard"), condition,
            ProjectCard.class);
    }

    @Override
    public long selectTotalCount(ProjectCard condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_projectCard_count"), condition);
    }

    @Override
    public List<ProjectCard> selectList(ProjectCard condition) {
        return super.selectList(NAMESPACE.concat("select_projectCard"),
            condition, ProjectCard.class);
    }

    @Override
    public List<ProjectCard> selectList(ProjectCard condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_projectCard"), start,
            count, condition, ProjectCard.class);
    }

    @Override
    public void update(ProjectCard data) {
        super.update(NAMESPACE.concat("update_projectCard"), data);
    }

}
