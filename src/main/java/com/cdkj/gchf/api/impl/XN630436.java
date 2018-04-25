package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.ICarOrderAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.CarOrder;
import com.cdkj.gchf.dto.req.XN630436Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 列表详情
 * @author: CYL 
 * @since: 2018年4月24日 下午5:40:39 
 * @history:
 */

public class XN630436 extends AProcessor {

    private ICarOrderAO carOrderAO = SpringContextHolder
        .getBean(ICarOrderAO.class);

    private XN630436Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        CarOrder condition = new CarOrder();
        condition.setBrandCode(req.getBrandCode());
        condition.setBrandName(req.getBrandName());
        condition.setSeriesCode(req.getSeriesCode());
        condition.setSeriesName(req.getSeriesName());
        condition.setCarCode(req.getCarCode());
        condition.setCarName(req.getCarName());
        condition.setSfRate(req.getSfRate());
        condition.setSfAmount(StringValidater.toLong(req.getSfAmount()));
        condition.setPeriods(req.getPeriods());
        condition.setStatus(req.getStatus());
        condition.setHandler(req.getHandler());

        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ICarOrderAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());

        return carOrderAO.queryCarOrderList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630436Req.class);
    }

}
