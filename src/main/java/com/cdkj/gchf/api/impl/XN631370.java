package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IBcontractAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631370Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

public class XN631370 extends AProcessor {

    private IBcontractAO bcontractAO = SpringContextHolder
        .getBean(IBcontractAO.class);

    private XN631370Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return bcontractAO.addBcontract(req);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631370Req.class);
        ObjValidater.validateReq(req);
    }

}
