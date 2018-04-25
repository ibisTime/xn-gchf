package com.cdkj.gchf.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IWithdrawDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.Withdraw;

@Repository("withdrawDAOImpl")
public class WithdrawDAOImpl extends AMybatisTemplate implements IWithdrawDAO {

    @Override
    public int insert(Withdraw data) {
        return super.insert(NAMESPACE.concat("insert_withdraw"), data);
    }

    @Override
    public int delete(Withdraw data) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Withdraw select(Withdraw condition) {
        return super.select(NAMESPACE.concat("select_withdraw"), condition,
            Withdraw.class);
    }

    @Override
    public long selectTotalCount(Withdraw condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_withdraw_count"), condition);
    }

    @Override
    public List<Withdraw> selectList(Withdraw condition) {
        return super.selectList(NAMESPACE.concat("select_withdraw"), condition,
            Withdraw.class);
    }

    @Override
    public List<Withdraw> selectList(Withdraw condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_withdraw"), start,
            count, condition, Withdraw.class);
    }

    @Override
    public void approveOrder(Withdraw data) {
        super.update(NAMESPACE.concat("approve_order"), data);
    }

    @Override
    public void payOrder(Withdraw data) {
        super.update(NAMESPACE.concat("pay_order"), data);
    }


    @Override
    public BigDecimal selectTotalWithdraw() {
        return super.select(NAMESPACE.concat("select_totalWithdraw"), null,
            BigDecimal.class);
    }

}
