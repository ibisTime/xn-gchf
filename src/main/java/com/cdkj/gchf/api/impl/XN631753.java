package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IBankCardInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631753Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;
import com.google.gson.JsonObject;

/**
 * @author : old3
 * @since : 2019-06-01 16:06
 */
public class XN631753 extends AProcessor {

    private XN631753Req req = null;

    private IBankCardInfoAO bankCardInfoAO = SpringContextHolder.getBean(IBankCardInfoAO.class);

    @Override
    public Object doBusiness() throws BizException {
        bankCardInfoAO.unBindBankCard(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631753Req.class);
        ObjValidater.validateReq(req);
    }
}

    
    