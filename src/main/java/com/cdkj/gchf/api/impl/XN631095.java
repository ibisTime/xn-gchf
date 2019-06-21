package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ISmsOutAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631095Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;
import org.omg.PortableInterceptor.INACTIVE;

/**
 * @author : old3
 * @since : 2019-06-12 21:48
 */
public class XN631095 extends AProcessor {

    private ISmsOutAO smsOutAO = SpringContextHolder.getBean(ISmsOutAO.class);
    private XN631095Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        smsOutAO.sendSmsCaptchaCheckUser(req.getMobile(), req.getBizType());
        smsOutAO.sendSmsCaptcha(req.getMobile(), req.getBizType());

        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631095Req.class);
        ObjValidater.validateReq(req);
    }
}

    
    