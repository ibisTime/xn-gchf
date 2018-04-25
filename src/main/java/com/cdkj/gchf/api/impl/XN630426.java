package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.ICarAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.domain.Car;
import com.cdkj.gchf.dto.req.XN630426Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 列表查询
 * @author: CYL 
 * @since: 2018年4月24日 下午5:39:48 
 * @history:
 */

public class XN630426 extends AProcessor {

    private ICarAO carAO = SpringContextHolder.getBean(ICarAO.class);

    private XN630426Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Car condition = new Car();
        condition.setName(req.getName());
        condition.setBrandCode(req.getBrandCode());
        condition.setBrandName(req.getBrandName());
        condition.setSeriesCode(req.getSeriesCode());
        condition.setSeriesName(req.getSeriesName());
        condition.setStatus(req.getStatus());

        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ICarAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());

        return carAO.queryCarList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630426Req.class);
    }

}
