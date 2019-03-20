package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IProjectCorpInfoDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.ProjectCorpInfo;

@Repository("projectCorpInfoDAOImpl")
public class ProjectCorpInfoDAOImpl extends AMybatisTemplate
        implements IProjectCorpInfoDAO {

    @Override
    public int insert(ProjectCorpInfo data) {
        return super.insert(NAMESPACE.concat("insert_projectCorpInfo"), data);
    }

    @Override
    public int delete(ProjectCorpInfo data) {
        return super.delete(NAMESPACE.concat("delete_projectCorpInfo"), data);
    }

    @Override
    public ProjectCorpInfo select(ProjectCorpInfo condition) {
        return super.select(NAMESPACE.concat("select_projectCorpInfo"),
            condition, ProjectCorpInfo.class);
    }

    @Override
    public long selectTotalCount(ProjectCorpInfo condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_projectCorpInfo_count"), condition);
    }

    @Override
    public List<ProjectCorpInfo> selectList(ProjectCorpInfo condition) {
        return super.selectList(NAMESPACE.concat("select_projectCorpInfo"),
            condition, ProjectCorpInfo.class);
    }

    @Override
    public List<ProjectCorpInfo> selectList(ProjectCorpInfo condition,
            int start, int count) {
        return super.selectList(NAMESPACE.concat("select_projectCorpInfo"),
            start, count, condition, ProjectCorpInfo.class);
    }

    @Override
    public int update(ProjectCorpInfo projectCorpInfo) {
        return super.update(NAMESPACE.concat("update_projectCorpInfo"), projectCorpInfo);
    }

}
