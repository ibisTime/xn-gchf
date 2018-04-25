package com.cdkj.gchf.ao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IAccountAO;
import com.cdkj.gchf.bo.IAccountBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Account;
import com.cdkj.gchf.dto.res.XN802503Res;
import com.cdkj.gchf.enums.EAccountType;
import com.cdkj.gchf.enums.EChannelType;
import com.cdkj.gchf.exception.BizException;

@Service
public class AccountAOImpl implements IAccountAO {

    @Autowired
    private IAccountBO accountBO;

    @Override
    @Transactional
    public void distributeAccount(String userId, String realName,
            String accountType, List<String> currencyList, String systemCode,
            String companyCode) {
        if (CollectionUtils.isNotEmpty(currencyList)) {
            Map<String, EAccountType> map = EAccountType
                .getAccountTypeResultMap();
            EAccountType eAccountType = map.get(accountType);
            if (null == eAccountType) {
                new BizException("XN0000", "账户类型不存在");
            }
            for (String currency : currencyList) {
                accountBO.distributeAccount(userId, realName, eAccountType,
                    currency, systemCode, companyCode);
            }
        }
    }

    @Override
    public void editAccountName(String userId, String realName) {
        // 验证用户名和系统编号
        Account condition = new Account();
        condition.setUserId(userId);
        List<Account> accountList = accountBO.queryAccountList(condition);
        if (CollectionUtils.isEmpty(accountList)) {
            new BizException("XN0000", "该用户无对应账号");
        }
        accountBO.refreshAccountName(userId, realName);
    }

    @Override
    @Transactional
    public void transAmountCZB(String fromUserId, String fromCurrency,
            String toUserId, String toCurrency, BigDecimal transAmount,
            String fromBizType, String toBizType, String fromBizNote,
            String toBizNote, String refNo) {
        accountBO.transAmountCZB(fromUserId, fromCurrency, toUserId,
            toCurrency, transAmount, fromBizType, toBizType, fromBizNote,
            toBizNote, refNo);
    }

    @Override
    public Paginable<Account> queryAccountPage(int start, int limit,
            Account condition) {
        return accountBO.getPaginable(start, limit, condition);
    }

    @Override
    public Account getAccount(String accountNumber) {
        return accountBO.getAccount(accountNumber);
    }

    @Override
    public XN802503Res getAccountByUserId(String userId, String currency) {
        XN802503Res res = new XN802503Res();
        Account condition = new Account();
        condition.setUserId(userId);
        condition.setCurrency(currency);
        List<Account> accountList = accountBO.queryAccountList(condition);
        res.setAccountList(accountList);
        return res;
    }

    @Override
    public List<Account> queryAccountList(Account condition) {
        return accountBO.queryAccountList(condition);
    }

    @Override
    public void changeAmount(String accountNumber, String channelType,
            String channelOrder, String payGroup, String refNo, String bizType,
            String bizNote, BigDecimal transAmount) {
        Account account = accountBO.getAccount(accountNumber);
        accountBO.changeAmount(account, transAmount,
            EChannelType.getEChannelType(channelType), channelOrder, payGroup,
            refNo, bizType, bizNote);
    }
}
