package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IBcontractDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.Bcontract;

//CHECK 。。。 
@Repository("bcontractDAOImpl")
public class BcontractDAOImpl extends AMybatisTemplate
        implements IBcontractDAO {

    @Override
    public int insert(Bcontract data) {
        return super.insert(NAMESPACE.concat("insert_bcontract"), data);
    }

    @Override
    public int delete(Bcontract data) {
        return 0;
    }

    @Override
    public Bcontract select(Bcontract condition) {
        return super.select(NAMESPACE.concat("select_bcontract"), condition,
            Bcontract.class);
    }

    @Override
    public long selectTotalCount(Bcontract condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_bcontract_count"), condition);
    }

    @Override
    public List<Bcontract> selectList(Bcontract condition) {
        return super.selectList(NAMESPACE.concat("select_bcontract"), condition,
            Bcontract.class);
    }

    @Override
    public List<Bcontract> selectList(Bcontract condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_bcontract"), start,
            count, condition, Bcontract.class);
    }

    @Override
    public int updateBcontract(Bcontract data) {
        return super.update(NAMESPACE.concat("update_bcontract"), data);
    }

}
