package com.cdkj.gchf.ao.impl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IExchangeCurrencyAO;
import com.cdkj.gchf.bo.IExchangeCurrencyBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ExchangeCurrency;
import com.cdkj.gchf.domain.User;

@Service
public class ExchangeCurrencyAOImpl implements IExchangeCurrencyAO {

    @Autowired
    private IUserBO userBO;


    @Autowired
    private IExchangeCurrencyBO exchangeCurrencyBO;


    @Override
    public Paginable<ExchangeCurrency> queryExchangeCurrencyPage(int start,
            int limit, ExchangeCurrency condition) {
        Paginable<ExchangeCurrency> page = exchangeCurrencyBO.getPaginable(
            start, limit, condition);
        if (page != null && CollectionUtils.isNotEmpty(page.getList())) {
            for (ExchangeCurrency exchangeCurrency : page.getList()) {
                User fromUser = userBO
                    .getUser(exchangeCurrency.getFromUserId());
                exchangeCurrency.setFromUser(fromUser);
                User toUser = userBO.getUser(exchangeCurrency.getToUserId());
                exchangeCurrency.setToUser(toUser);
            }
        }
        return page;
    }

    @Override
    public ExchangeCurrency getExchangeCurrency(String code) {
        ExchangeCurrency exchangeCurrency = exchangeCurrencyBO
            .getExchangeCurrency(code);
        User fromUser = userBO.getUser(exchangeCurrency.getFromUserId());
        exchangeCurrency.setFromUser(fromUser);
        return exchangeCurrency;
    }

    @Override
    public Double getExchangeRate(String fromCurrency, String toCurrency) {
        return exchangeCurrencyBO.getExchangeRate(fromCurrency, toCurrency);
    }

    // @Override
    // @Transactional
    // public void doTransfer(String fromUserId, String fromCurrency,
    // String toUserId, String toCurrency, BigDecimal transAmount) {
    // // 转化前提是否满足
    // if (ECurrency.CNY.getCode().equals(toCurrency)) {
    // throw new BizException("xn000000", "转化币种不能是人民币");
    // }
    // Account fromAccount = accountBO.getAccountByUser(fromUserId,
    // fromCurrency);
    // Account toAccount = accountBO.getAccountByUser(toUserId, toCurrency);
    // Double rate = this.getExchangeRate(fromCurrency, toCurrency);
    // BigDecimal toAmount = AmountUtil.mul(transAmount, rate);
    //
    // // 开始资金划转
    // String bizNote = transAmount
    // + ECurrency.getCurrencyMap().get(fromCurrency).getValue()
    // + "转化为" + toAmount
    // + ECurrency.getCurrencyMap().get(toCurrency).getValue();
    // String fromBizNote = bizNote;
    // String toBizNote = bizNote;
    // if (fromCurrency.equals(toCurrency)) {
    // fromBizNote = "代发代销至账户[" + toAccount.getRealName() + "]";
    // toBizNote = "账户[" + fromAccount.getRealName() + "]代发代销";
    // }
    // String code = exchangeCurrencyBO.saveExchange(fromUserId, transAmount,
    // fromCurrency, toUserId, toAmount, toCurrency,
    // fromAccount.getCompanyCode(), fromAccount.getSystemCode());
    // accountBO.changeAmount(fromAccount.getAccountNumber(),
    // EChannelType.NBZ, null, null, code, EJourBizType.Transfer_CURRENCY,
    // fromBizNote, transAmount.negate());
    // accountBO.changeAmount(toAccount.getAccountNumber(), EChannelType.NBZ,
    // null, null, code, EJourBizType.Transfer_CURRENCY, toBizNote,
    // toAmount);
    // }

}
