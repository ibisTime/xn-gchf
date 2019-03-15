package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.ICcontractDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.Ccontract;

@Repository("ccontractDAOImpl")
public class CcontractDAOImpl extends AMybatisTemplate
        implements ICcontractDAO {

    @Override
    public int insert(Ccontract data) {
        return super.insert(NAMESPACE.concat("insert_ccontract"), data);
    }

    @Override
    public int delete(Ccontract data) {
        return super.delete(NAMESPACE.concat("delete_ccontract"), data);
    }

    @Override
    public Ccontract select(Ccontract condition) {
        return super.select(NAMESPACE.concat("select_ccontract"), condition,
            Ccontract.class);
    }

    @Override
    public long selectTotalCount(Ccontract condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_ccontract_count"), condition);
    }

    @Override
    public List<Ccontract> selectList(Ccontract condition) {
        return super.selectList(NAMESPACE.concat("select_ccontract"), condition,
            Ccontract.class);
    }

    @Override
    public List<Ccontract> selectList(Ccontract condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_ccontract"), start,
            count, condition, Ccontract.class);
    }

    @Override
    public void update(Ccontract data) {
        super.update(NAMESPACE.concat("update_ccontract"), data);
    }

}
