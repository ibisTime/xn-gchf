package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProjectWorkerAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631697Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * @author : old3
 * @since : 2019-05-27 21:58
 */
public class XN631697 extends AProcessor {

    private XN631697Req req = null;

    private IProjectWorkerAO projectWorkerAO = SpringContextHolder.getBean(IProjectWorkerAO.class);

    @Override
    public Object doBusiness() throws BizException {
        return projectWorkerAO.select30DayData(req.getUserId());
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631697Req.class);
        ObjValidater.validateReq(req);
    }
}

    
    