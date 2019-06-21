package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IBankCardInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631769Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * @author : old3
 * @since : 2019-06-11 21:02
 */
public class XN631769 extends AProcessor {

    private IBankCardInfoAO bankCardInfoAO = SpringContextHolder.getBean(IBankCardInfoAO.class);

    private XN631769Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return bankCardInfoAO.queryProjectCorpBankcards(req.getUserId(), req.getWorkerCode());
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631769Req.class);
        ObjValidater.validateReq(req);
    }
}

    
    