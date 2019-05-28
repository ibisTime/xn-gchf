package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProjectWorkerAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631603Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * @author : old3
 * @since : 2019-05-27 20:51
 */
public class XN631603 extends AProcessor {

    private XN631603Req req = null;


    private IProjectWorkerAO projectWorkerAO = SpringContextHolder.getBean(IProjectWorkerAO.class);

    @Override
    public Object doBusiness() throws BizException {
        return projectWorkerAO.selectData(req.getUserId());
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631603Req.class);
        ObjValidater.validateReq(req);
    }
}

    
    