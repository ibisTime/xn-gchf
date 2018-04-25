package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.ExchangeCurrency;

public interface IExchangeCurrencyDAO extends IBaseDAO<ExchangeCurrency> {
    String NAMESPACE = IExchangeCurrencyDAO.class.getName().concat(".");

    int applyExchange(ExchangeCurrency data);

    int approveExchange(ExchangeCurrency data);

    int doExchange(ExchangeCurrency data);

    int paySuccess(ExchangeCurrency data);

}
