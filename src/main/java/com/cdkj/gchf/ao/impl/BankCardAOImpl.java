package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IBankCardAO;
import com.cdkj.gchf.bo.IBankCardBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.BankCard;

@Service
public class BankCardAOImpl implements IBankCardAO {

    @Autowired
    private IBankCardBO bankCardBO;

    @Override
    public void editBankCard(BankCard data) {
    }

    @Override
    public void dropBankCard(String code) {
    }

    @Override
    public Paginable<BankCard> queryBankCardPage(int start, int limit,
            BankCard condition) {
        return bankCardBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<BankCard> queryBankCardList(BankCard condition) {
        return bankCardBO.queryBankCardList(condition);
    }

    @Override
    public BankCard getBankCard(String code) {
        return bankCardBO.getBankCard(code);
    }

}
