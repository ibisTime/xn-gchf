package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IBcontractAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.dto.req.XN631372Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

public class XN631372 extends AProcessor {
    private IBcontractAO bcontractAO = SpringContextHolder
        .getBean(IBcontractAO.class);

    private XN631372Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        bcontractAO.editBcontract(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        // TODO Auto-generated method stub

    }

}
