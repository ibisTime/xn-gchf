package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.BankCardInfo;
import com.cdkj.gchf.dto.req.XN631750Req;
import com.cdkj.gchf.dto.req.XN631752Req;

public interface IBankCardBankBO extends IPaginableBO<BankCardInfo> {

    public String saveBankCardInfo(XN631750Req req);

    public void removeBankCardInfo(String code);

    public void refreshBankCardInfo(XN631752Req req);

    public List<BankCardInfo> queryBankCardInfoList(BankCardInfo condition);

    public BankCardInfo getBankCardInfo(Long id);

    public BankCardInfo getBankCardInfo(String bankCode);
}
