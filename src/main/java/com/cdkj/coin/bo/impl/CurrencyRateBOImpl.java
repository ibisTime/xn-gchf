package com.cdkj.coin.bo.impl;

import com.cdkj.coin.bo.ICurrencyRateBO;
import com.cdkj.coin.common.StringUtil;
import com.cdkj.coin.dao.ICNavigateDAO;
import com.cdkj.coin.dao.ICurrencyRateDAO;
import com.cdkj.coin.domain.CurrencyRate;
import com.cdkj.coin.enums.ECurrency;
import com.cdkj.coin.exception.BizException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by tianlei on 2017/十一月/13.
 */
@Component
public class CurrencyRateBOImpl implements ICurrencyRateBO {

    @Autowired
    ICurrencyRateDAO currencyRateDAO;

    public CurrencyRate currencyRateByCurrency(String currency) {

        if (StringUtils.isBlank(currency)) {
            throw new BizException("xn000000", "传入货币类型");
        }
        CurrencyRate condation = new CurrencyRate();
        condation.setCurrency(currency);
        return this.currencyRateDAO.select(condation);

    }

    @Override
    public List<CurrencyRate> allCurrencyRate() {

        return this.currencyRateDAO.selectList(null);

    }

    @Override
    public int changeRate(String currency, BigDecimal rate, String origin) {

        if (StringUtils.isBlank(currency) || rate == null || StringUtils.isBlank(origin)) {
            throw new BizException("xn0000","参数不符合要求");
        }
        CurrencyRate condation = new CurrencyRate();
        condation.setCurrency(currency);
        condation.setRate(rate);
        condation.setUpdateDatetime(new Date());
        condation.setReferCurrency(ECurrency.CNY.getCode());
        condation.setOrigin(origin);
        return this.currencyRateDAO.update(condation);

    }
}