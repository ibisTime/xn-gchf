package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IBankCardDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.BankCard;

@Repository("bankCardDAOImpl")
public class BankCardDAOImpl extends AMybatisTemplate implements IBankCardDAO {

    @Override
    public int insert(BankCard data) {
        return super.insert(NAMESPACE.concat("insert_bankCard"), data);
    }

    @Override
    public int delete(BankCard data) {
        return super.delete(NAMESPACE.concat("delete_bankCard"), data);
    }

    @Override
    public BankCard select(BankCard condition) {
        return super.select(NAMESPACE.concat("select_bankCard"), condition,
            BankCard.class);
    }

    @Override
    public long selectTotalCount(BankCard condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_bankCard_count"),
            condition);
    }

    @Override
    public List<BankCard> selectList(BankCard condition) {
        return super.selectList(NAMESPACE.concat("select_bankCard"), condition,
            BankCard.class);
    }

    @Override
    public List<BankCard> selectList(BankCard condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_bankCard"), start,
            count, condition, BankCard.class);
    }

    @Override
    public void update(BankCard data) {
        super.update(NAMESPACE.concat("update_bankCard"), data);
    }

}
