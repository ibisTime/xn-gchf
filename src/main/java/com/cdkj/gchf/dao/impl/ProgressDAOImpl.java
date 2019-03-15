package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IProgressDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.Progress;

//CHECK 。。。 
@Repository("progressDAOImpl")
public class ProgressDAOImpl extends AMybatisTemplate implements IProgressDAO {

    @Override
    public int insert(Progress data) {
        return super.insert(NAMESPACE.concat("insert_Progress"), data);
    }

    @Override
    public int delete(Progress data) {
        return 0;
    }

    @Override
    public int updateProgress(Progress data) {
        return super.delete(NAMESPACE.concat("update_Progress"), data);
    }

    @Override
    public Progress select(Progress condition) {
        return super.select(NAMESPACE.concat("select_Progress"), condition,
            Progress.class);
    }

    @Override
    public long selectTotalCount(Progress condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_Progress_count"),
            condition);
    }

    @Override
    public List<Progress> selectList(Progress condition) {
        return super.selectList(NAMESPACE.concat("select_Progress"), condition,
            Progress.class);
    }

    @Override
    public List<Progress> selectList(Progress condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_Progress"), start,
            count, condition, Progress.class);
    }

}
