package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProjectWorkerAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631695Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

public class XN631695 extends AProcessor {
    private IProjectWorkerAO projectWorkerAO = SpringContextHolder
        .getBean(IProjectWorkerAO.class);

    private XN631695Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        projectWorkerAO.updatePlantformProjectWorker(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631695Req.class);
        ObjValidater.validateReq(req);
    }

}
