package com.cdkj.gchf.bo;

import java.util.Date;
import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.BankCard;

public interface IBankCardBO extends IPaginableBO<BankCard> {

    public void removeBankCard(String code);

    public void refreshBankCard(BankCard data);

    public List<BankCard> queryBankCardList(BankCard condition);

    public BankCard getBankCard(String code);

    public void addBankCard(String code, String name, String bankCode,
            String bankName, String bankcardNumber, String subbranch,
            String updater, Date updateDatetime, String remark);

    public BankCard getBankCardByStaff(String code);

}
