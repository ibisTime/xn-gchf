package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ILeaveAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631461Req;
import com.cdkj.gchf.dto.res.PKCodeRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 请假
 * @author: nyc 
 * @since: 2018年4月29日 下午7:32:21 
 * @history:
 */
public class XN631461 extends AProcessor {

    private ILeaveAO leaveAO = SpringContextHolder.getBean(ILeaveAO.class);

    private XN631461Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(leaveAO.addLeave(req));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631461Req.class);
        ObjValidater.validateReq(req);
    }

}
