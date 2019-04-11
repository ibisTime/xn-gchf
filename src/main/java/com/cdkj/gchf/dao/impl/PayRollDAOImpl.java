package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IPayRollDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.PayRoll;

@Repository("payRollDAOImpl")
public class PayRollDAOImpl extends AMybatisTemplate implements IPayRollDAO {

    @Override
    public int insert(PayRoll data) {
        return super.insert(NAMESPACE.concat("insert_payRoll"), data);
    }

    @Override
    public int delete(PayRoll data) {
        return super.delete(NAMESPACE.concat("delete_payRoll"), data);
    }

    @Override
    public PayRoll select(PayRoll condition) {
        return super.select(NAMESPACE.concat("select_payRoll"), condition,
            PayRoll.class);
    }

    @Override
    public long selectTotalCount(PayRoll condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_payRoll_count"),
            condition);
    }

    @Override
    public List<PayRoll> selectList(PayRoll condition) {
        return super.selectList(NAMESPACE.concat("select_payRoll"), condition,
            PayRoll.class);
    }

    @Override
    public List<PayRoll> selectList(PayRoll condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_payRoll"), start,
            count, condition, PayRoll.class);
    }

    @Override
    public int update(PayRoll payRoll) {
        return super.update(NAMESPACE.concat("update_payRoll"), payRoll);
    }

    @Override
    public int updatePayRollCode(PayRoll payRoll) {
        return super.update(NAMESPACE.concat("update_payRoll"), payRoll);
    }

    @Override
    public int updatePayRollDeleteStatus(PayRoll payRoll) {
        return super.update(NAMESPACE.concat("update_payRoll_delete_status"),
            payRoll);
    }

}
