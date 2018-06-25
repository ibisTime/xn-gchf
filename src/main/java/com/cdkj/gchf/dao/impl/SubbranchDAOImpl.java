package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.ISubbranchDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.Subbranch;

@Repository("subbranchDAOImpl")
public class SubbranchDAOImpl extends AMybatisTemplate
        implements ISubbranchDAO {

    @Override
    public int insert(Subbranch data) {
        return super.insert(NAMESPACE.concat("insert_subbranch"), data);
    }

    @Override
    public int delete(Subbranch data) {
        return super.delete(NAMESPACE.concat("delete_subbranch"), data);
    }

    @Override
    public Subbranch select(Subbranch condition) {
        return super.select(NAMESPACE.concat("select_subbranch"), condition,
            Subbranch.class);
    }

    @Override
    public long selectTotalCount(Subbranch condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_subbranch_count"), condition);
    }

    @Override
    public List<Subbranch> selectList(Subbranch condition) {
        return super.selectList(NAMESPACE.concat("select_subbranch"), condition,
            Subbranch.class);
    }

    @Override
    public List<Subbranch> selectList(Subbranch condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_subbranch"), start,
            count, condition, Subbranch.class);
    }

}
