package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IBankCardAO;
import com.cdkj.gchf.bo.IBankCardBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.BankCard;
import com.cdkj.gchf.dto.req.XN631422Req;

@Service
public class BankCardAOImpl implements IBankCardAO {

    @Autowired
    private IBankCardBO bankCardBO;

    @Override
    public void editBankCard(XN631422Req req) {

        BankCard data = bankCardBO.getBankCard(req.getCode());
        data.setBankCode(req.getBankCode());
        data.setBankName(req.getBankName());
        data.setSubbranch(req.getSubbranch());
        data.setBankcardNumber(req.getBackCardNumber());

        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        bankCardBO.refreshBankCard(data);
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
