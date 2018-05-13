package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.BankCard;

public interface IBankCardBO extends IPaginableBO<BankCard> {

    public void removeBankCard(String code);

    public void refreshBankCard(BankCard data);

    public List<BankCard> queryBankCardList(BankCard condition);

    public BankCard getBankCard(String code);

    public void addBankCard(BankCard data);

    public BankCard getBankCardByStaff(String code);

}
