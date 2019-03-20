package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IProjectBuilderLicenseDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.ProjectBuilderLicense;

@Repository("projectBuilderLicenseDAOImpl")
public class ProjectBuilderLicenseDAOImpl extends AMybatisTemplate
        implements IProjectBuilderLicenseDAO {

    @Override
    public int insert(ProjectBuilderLicense data) {
        return super.insert(NAMESPACE.concat("insert_projectBuilderLicense"),
            data);
    }

    @Override
    public int delete(ProjectBuilderLicense data) {
        return super.delete(NAMESPACE.concat("delete_projectBuilderLicense"),
            data);
    }

    @Override
    public ProjectBuilderLicense select(ProjectBuilderLicense condition) {
        return super.select(NAMESPACE.concat("select_projectBuilderLicense"),
            condition, ProjectBuilderLicense.class);
    }

    @Override
    public long selectTotalCount(ProjectBuilderLicense condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_projectBuilderLicense_count"), condition);
    }

    @Override
    public List<ProjectBuilderLicense> selectList(
            ProjectBuilderLicense condition) {
        return super.selectList(
            NAMESPACE.concat("select_projectBuilderLicense"), condition,
            ProjectBuilderLicense.class);
    }

    @Override
    public List<ProjectBuilderLicense> selectList(
            ProjectBuilderLicense condition, int start, int count) {
        return super.selectList(
            NAMESPACE.concat("select_projectBuilderLicense"), start, count,
            condition, ProjectBuilderLicense.class);
    }

    @Override
    public int deleteByProject(ProjectBuilderLicense condition) {
        return super.delete(NAMESPACE.concat("delete_byProject"), condition);
    }

}
