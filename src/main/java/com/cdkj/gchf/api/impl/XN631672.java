package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IWorkerContractAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631672Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

public class XN631672 extends AProcessor {
    private IWorkerContractAO workerContractAO = SpringContextHolder
        .getBean(IWorkerContractAO.class);

    private XN631672Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        workerContractAO.editWorkerContract(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631672Req.class);
        ObjValidater.validateReq(req);
    }

}
