package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.BankCardInfo;
import com.cdkj.gchf.dto.req.XN631750Req;
import com.cdkj.gchf.dto.req.XN631751Req;
import com.cdkj.gchf.dto.req.XN631752Req;
import com.cdkj.gchf.dto.req.XN631767Req;

@Component
public interface IBankCardInfoAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addBankCardInfo(XN631750Req req);

    public void changeBankCardStatus(XN631751Req req);

    public void editBankCardInfo(XN631752Req req);

    public void queryBankCardInfoDetail(String code);

    public Paginable<BankCardInfo> queryBankCardInfoPage(String userId,
            int start, int limit, BankCardInfo condition);

    public List<BankCardInfo> queryBankCardInfoList(XN631767Req req);

    public BankCardInfo getBankCardInfo(String code);

}
