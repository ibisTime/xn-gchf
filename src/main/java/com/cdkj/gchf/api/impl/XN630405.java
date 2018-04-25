package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.IBrandAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.Brand;
import com.cdkj.gchf.dto.req.XN630405Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 分页查询
 * @author: CYL 
 * @since: 2018年4月24日 下午5:35:06 
 * @history:
 */

public class XN630405 extends AProcessor {

    private IBrandAO brandAO = SpringContextHolder.getBean(IBrandAO.class);

    private XN630405Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Brand condition = new Brand();
        condition.setName(req.getName());
        condition.setLetter(req.getLetter());
        condition.setStatus(req.getStatus());

        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IBrandAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return brandAO.queryBrandPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630405Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
