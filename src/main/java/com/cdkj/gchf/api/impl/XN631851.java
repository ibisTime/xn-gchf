package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProjectCameraAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631851Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * @author : old3
 * @since : 2019-06-21 1:45
 */
public class XN631851 extends AProcessor {

    private XN631851Req req = null;

    private IProjectCameraAO projectCameraAO = SpringContextHolder.getBean(IProjectCameraAO.class);

    @Override
    public Object doBusiness() throws BizException {
        projectCameraAO.dropProjectCamera(req.getCodeList());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631851Req.class);
        ObjValidater.validateReq(req);
    }
}

    
    