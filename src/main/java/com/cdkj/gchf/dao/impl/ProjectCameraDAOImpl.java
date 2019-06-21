package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IProjectCameraDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.ProjectCamera;


//CHECK 。。。
@Repository("projectCameraDAOImpl")
public class ProjectCameraDAOImpl extends AMybatisTemplate implements IProjectCameraDAO {


    @Override
    public int insert(ProjectCamera data) {
        return super.insert(NAMESPACE.concat("insert_ProjectCamera"), data);
    }


    @Override
    public int delete(ProjectCamera data) {
        return super.delete(NAMESPACE.concat("delete_ProjectCamera"), data);
    }


    @Override
    public ProjectCamera select(ProjectCamera condition) {
        return super
                .select(NAMESPACE.concat("select_ProjectCamera"), condition, ProjectCamera.class);
    }


    @Override
    public long selectTotalCount(ProjectCamera condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_ProjectCamera_count"), condition);
    }


    @Override
    public List<ProjectCamera> selectList(ProjectCamera condition) {
        return super.selectList(NAMESPACE.concat("select_ProjectCamera"), condition,
                ProjectCamera.class);
    }


    @Override
    public List<ProjectCamera> selectList(ProjectCamera condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_ProjectCamera"), start, count, condition,
                ProjectCamera.class);
    }


    @Override
    public int update(ProjectCamera projectCamera) {
        return super.update(NAMESPACE.concat("update_ProjectCamera"), projectCamera);
    }
}