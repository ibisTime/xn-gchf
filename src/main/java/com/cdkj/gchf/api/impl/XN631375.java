package com.cdkj.gchf.api.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.IBcontractAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.Bcontract;
import com.cdkj.gchf.dto.req.XN631375Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

public class XN631375 extends AProcessor {
    private IBcontractAO bcontractAO = SpringContextHolder
        .getBean(IBcontractAO.class);

    private XN631375Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Bcontract condition = new Bcontract();
        condition.setProjectCode(req.getProjectCode());
        condition.setUpdater(req.getUpdater());
        condition.setUpdateDatetime(new Date());
        condition.setKeyword(req.getKeyword());
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = bcontractAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return bcontractAO.queryBcontractPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631375Req.class);
        StringValidater.validateBlank(req.getStart(), req.getLimit());
    }

}
