package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.BankCard;
import com.cdkj.gchf.dto.req.XN631420Req;
import com.cdkj.gchf.dto.req.XN631422Req;

@Component
public interface IBankCardAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 添加工资卡
    public String addBankCard(XN631420Req req);

    public void editBankCard(XN631422Req req);

    // 判断员工工资卡是否存在
    public boolean isBankCardExist(String staffCode, String companyCode);

    public Paginable<BankCard> queryBankCardPage(int start, int limit,
            BankCard condition);

    public List<BankCard> queryBankCardList(BankCard condition);

    public BankCard getBankCard(String code);

}
