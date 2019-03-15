package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IOperatorGuideAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631127Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 详细查询操作指南
 * @author: silver 
 * @since: 2018年8月7日 上午9:48:21 
 * @history:
 */
public class XN631127 extends AProcessor {

    private IOperatorGuideAO operatorGuideAO = SpringContextHolder
        .getBean(IOperatorGuideAO.class);

    private XN631127Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return operatorGuideAO.getOperatorGuide(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631127Req.class);
        ObjValidater.validateReq(req);
    }

}
