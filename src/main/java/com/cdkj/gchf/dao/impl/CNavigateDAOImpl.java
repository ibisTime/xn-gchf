package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.ICNavigateDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.CNavigate;

@Repository("cNavigateDAOImpl")
public class CNavigateDAOImpl extends AMybatisTemplate implements ICNavigateDAO {

    @Override
    public int insert(CNavigate data) {
        return super.insert(NAMESPACE.concat("insert_cNavigate"), data);
    }

    @Override
    public int delete(CNavigate data) {
        return super.delete(NAMESPACE.concat("delete_cNavigate"), data);
    }

    @Override
    public CNavigate select(CNavigate condition) {
        return super.select(NAMESPACE.concat("select_cNavigate"), condition,
            CNavigate.class);
    }

    @Override
    public long selectTotalCount(CNavigate condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_cNavigate_count"), condition);
    }

    @Override
    public List<CNavigate> selectList(CNavigate condition) {
        return super.selectList(NAMESPACE.concat("select_cNavigate"),
            condition, CNavigate.class);
    }

    @Override
    public List<CNavigate> selectList(CNavigate condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_cNavigate"), start,
            count, condition, CNavigate.class);
    }

    @Override
    public int update(CNavigate data) {
        return super.update(NAMESPACE.concat("update_cNavigate"), data);
    }
}
