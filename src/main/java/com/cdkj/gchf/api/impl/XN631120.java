package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IOperatorGuideAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631120Req;
import com.cdkj.gchf.dto.res.PKCodeRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 新增操作指南
 * @author: silver 
 * @since: 2018年8月6日 下午5:05:56 
 * @history:
 */
public class XN631120 extends AProcessor {

    private IOperatorGuideAO operatorGuideAO = SpringContextHolder
        .getBean(IOperatorGuideAO.class);

    private XN631120Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(operatorGuideAO.addOperatorGuide(req));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631120Req.class);
        ObjValidater.validateReq(req);
    }

}
