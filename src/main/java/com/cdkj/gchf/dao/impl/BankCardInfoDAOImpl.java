package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IBankCardInfoDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.BankCardInfo;

@Repository(value = "bankCardInfoDAOImpl")
public class BankCardInfoDAOImpl extends AMybatisTemplate
        implements IBankCardInfoDAO {

    @Override
    public int insert(BankCardInfo data) {
        return super.insert(NAMESPACE.concat("insert_bankCardInfo"), data);
    }

    @Override
    public int delete(BankCardInfo data) {
        return super.delete(NAMESPACE.concat("delete_bankCardInfo"), data);
    }

    @Override
    public BankCardInfo select(BankCardInfo condition) {
        return super.select(NAMESPACE.concat("select_bankCardInfo"), condition,
            BankCardInfo.class);
    }

    @Override
    public long selectTotalCount(BankCardInfo condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_bankCardInfo_count"), condition);
    }

    @Override
    public List<BankCardInfo> selectList(BankCardInfo condition) {
        return super.selectList(NAMESPACE.concat("select_bankCardInfo"),
            condition, BankCardInfo.class);
    }

    @Override
    public List<BankCardInfo> selectList(BankCardInfo condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_bankCardInfo"), start,
            count, condition, BankCardInfo.class);
    }

    @Override
    public int updateBankCardInfo(BankCardInfo condition) {
        return super.update(NAMESPACE.concat("update_bankCardInfo"), condition);
    }

    @Override
    public int updateBankCardInfoStatus(BankCardInfo condition) {
        return super.update(NAMESPACE.concat("update_bankCardInfoStatus"),
            condition);
    }

    @Override
    public void updateBankCardInfoStatusByBussiness(BankCardInfo condition) {
        super.update(NAMESPACE.concat("update_bankCardInfoStatusByBussiness"),
            condition);
    }

    @Override
    public List<BankCardInfo> selectBankCardByIdcard(List<String> idcards) {
        return super.selectList(
            NAMESPACE.concat("select_bankCardInfoByIdcardNumbers"), idcards,
            BankCardInfo.class);
    }

}
