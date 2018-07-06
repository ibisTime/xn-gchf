package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.BankCard;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.dto.req.XN631413Req;

public interface IBankCardBO extends IPaginableBO<BankCard> {
    public void addBankCard(XN631413Req req, Staff staff);

    // 更新银行卡信息
    public void refreshBankCard(String code, String bankCode, String bankName,
            String subbranch, String bankcardNumber, String updater,
            String remark);

    public List<BankCard> queryBankCardList(BankCard condition);

    public BankCard getBankCard(String code);

    public BankCard getBankCardByStaff(String code);

}
