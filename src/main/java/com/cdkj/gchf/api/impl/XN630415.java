package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.ISeriesAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.Series;
import com.cdkj.gchf.dto.req.XN630415Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 分页查询
 * @author: CYL 
 * @since: 2018年4月24日 下午5:37:48 
 * @history:
 */

public class XN630415 extends AProcessor {

    private ISeriesAO seriesAO = SpringContextHolder.getBean(ISeriesAO.class);

    private XN630415Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Series condition = new Series();
        condition.setName(req.getName());
        condition.setBrandCode(req.getBrandCode());
        condition.setStatus(req.getStatus());

        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ISeriesAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return seriesAO.querySeriesPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630415Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
