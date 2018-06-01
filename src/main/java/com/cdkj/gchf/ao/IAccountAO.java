package com.cdkj.gchf.ao;

import java.math.BigDecimal;
import java.util.List;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Account;
import com.cdkj.gchf.dto.res.XN802503Res;

public interface IAccountAO {
    String DEFAULT_ORDER_COLUMN = "account_number";

    // 个人创建多账户
    public void distributeAccount(String userId, String realName,
            String accountType, List<String> currencyList, String systemCode,
            String companyCode);

    // 更新户名
    public void editAccountName(String userId, String realName);

    // 不同用户不同币种间资金划转
    void transAmountCZB(String fromUserId, String fromCurrency, String toUserId,
            String toCurrency, BigDecimal transAmount, String fromBizType,
            String toBizType, String fromBizNote, String toBizNote,
            String refNo);

    // 分页查询账户
    public Paginable<Account> queryAccountPage(int start, int limit,
            Account condition);

    // 根据accountNumber查询账户
    public Account getAccount(String accountNumber);

    // 根据用户编号,币种获取账户列表
    public XN802503Res getAccountByUserId(String userId, String currency);

    // 列表查询账户
    public List<Account> queryAccountList(Account condition);

    public void changeAmount(String accountNumber, String channelType,
            String channelOrder, String payGroup, String refNo, String bizType,
            String bizNote, BigDecimal transAmount);

}
