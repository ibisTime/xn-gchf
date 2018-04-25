package com.cdkj.gchf.dao.impl;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IMarketDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.Market;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by tianlei on 2017/十一月/13.
 */
@Repository("marketDAOImpl")
public class MarketDAOImpl extends AMybatisTemplate implements IMarketDAO {

    @Override
    public int insert(Market data) {
        return 0;
    }

    @Override
    public int delete(Market data) {
        return 0;
    }

    @Override
    public long selectTotalCount(Market condition) {
        return super.selectTotalCount(NAMESPACE.concat("selectTotalCount"), condition);
    }

    @Override
    public Market select(Market condition) {

        return super.select(NAMESPACE.concat("selectedByCoinTypeAndOrigin"), condition, Market.class);

    }

    @Override
    public BigDecimal selectMarketAvg(Market condition) {

        return super.select(NAMESPACE.concat("selectMarketAvg"), condition, BigDecimal.class);

    }



    @Override
    public int update(Market market) {
        return super.update(NAMESPACE.concat("update"), market);
    }

    @Override
    public List<Market> selectList(Market condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select"), start, count, condition, Market.class);
    }

    @Override
    public List<Market> selectList(Market condition) {

        return super.selectList(NAMESPACE.concat("select"), condition, Market.class);

    }
}

