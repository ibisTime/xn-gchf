package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ISmsOutAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.common.PhoneUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN630080Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 发送验证码
 * @author: nyc 
 * @since: 2018年4月25日 上午10:52:10 
 * @history:
 */
public class XN630080 extends AProcessor {
    private ISmsOutAO smsOutAO = SpringContextHolder.getBean(ISmsOutAO.class);

    private XN630080Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        smsOutAO.sendSmsCaptcha(req.getMobile(), req.getBizType());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630080Req.class);
        ObjValidater.validateReq(req);
        PhoneUtil.checkMobile(req.getMobile(), "请输入正确的手机号码");
    }
}
