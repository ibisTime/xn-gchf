package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IMessageAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631431Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 发送代发消息
 * @author: nyc 
 * @since: 2018年5月1日 上午11:48:15 
 * @history:
 */
public class XN631431 extends AProcessor {

    private IMessageAO messageAO = SpringContextHolder
        .getBean(IMessageAO.class);

    private XN631431Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        messageAO.approveMessage(req.getCode(), req.getHandler(),
            req.getHandleNote(), req.getPayList());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631431Req.class);
        ObjValidater.validateReq(req);
    }

}
