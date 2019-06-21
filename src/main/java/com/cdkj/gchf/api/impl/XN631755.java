package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IBankCardInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631755Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * @author : old3
 * @since : 2019-06-21 14:24
 */
public class XN631755 extends AProcessor {

    private XN631755Req req = null;

    private IBankCardInfoAO bankCardInfoAO = SpringContextHolder.getBean(IBankCardInfoAO.class);

    @Override
    public Object doBusiness() throws BizException {
        return bankCardInfoAO.selectWorkerBankCards(req.getWorkerCode());
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631755Req.class);
        ObjValidater.validateReq(req);
    }
}

    
    