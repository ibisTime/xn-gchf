package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProjectCameraAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631854Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * @author: Xiongk
 * @since: 2019-07-03 12:30
 */
public class XN631854 extends AProcessor {

    private XN631854Req req = null;

    private IProjectCameraAO projectCameraAO = SpringContextHolder.getBean(IProjectCameraAO.class);

    @Override
    public Object doBusiness() throws BizException {
         projectCameraAO.releaseHlsResource(req.getUserId());
         return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631854Req.class);
        ObjValidater.validateReq(req);
    }
}

    
    