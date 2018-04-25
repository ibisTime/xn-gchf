package com.cdkj.gchf.dao;

import java.math.BigDecimal;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Market;

/**
 * Created by tianlei on 2017/十一月/13.
 */
public interface IMarketDAO extends IBaseDAO<Market> {

    String NAMESPACE = IMarketDAO.class.getName().concat(".");

    int update(Market market);

    BigDecimal selectMarketAvg(Market condition);

}
