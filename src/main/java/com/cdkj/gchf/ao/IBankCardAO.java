package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.BankCard;

@Component
public interface IBankCardAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public void dropBankCard(String code);

    public void editBankCard(BankCard data);

    public Paginable<BankCard> queryBankCardPage(int start, int limit,
            BankCard condition);

    public List<BankCard> queryBankCardList(BankCard condition);

    public BankCard getBankCard(String code);

}
