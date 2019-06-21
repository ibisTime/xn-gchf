package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProjectCameraAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631850Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * @author : old3
 * @since : 2019-06-21 0:58
 */
public class XN631850 extends AProcessor {

    private XN631850Req req = null;

    private IProjectCameraAO projectCameraAO = SpringContextHolder.getBean(IProjectCameraAO.class);

    @Override
    public Object doBusiness() throws BizException {
        return projectCameraAO.addProjectCamera(req);
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631850Req.class);
        ObjValidater.validateReq(req);
    }
}

    
    