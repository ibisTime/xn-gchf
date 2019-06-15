package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProjectWorkerAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631610Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * @author : old3
 * @since : 2019-06-10 20:18
 */
public class XN631610 extends AProcessor {

    private IProjectWorkerAO projectWorkerAO = SpringContextHolder.getBean(IProjectWorkerAO.class);

    private XN631610Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return projectWorkerAO.queryProjectWorkerH5(req.getUserId(), req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631610Req.class);
        ObjValidater.validateReq(req);
    }
}

    
    