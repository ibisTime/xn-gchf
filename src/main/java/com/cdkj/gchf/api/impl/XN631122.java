package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IOperatorGuideAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631122Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 编辑操作指南
 * @author: silver 
 * @since: 2018年8月6日 下午5:05:56 
 * @history:
 */
public class XN631122 extends AProcessor {

    private IOperatorGuideAO operatorGuideAO = SpringContextHolder
        .getBean(IOperatorGuideAO.class);

    private XN631122Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        operatorGuideAO.editOperatorGuide(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631122Req.class);
        ObjValidater.validateReq(req);
    }

}
