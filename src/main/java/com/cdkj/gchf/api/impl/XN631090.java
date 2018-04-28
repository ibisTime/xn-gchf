package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ISmsOutAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631090Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 发送短信验证码
 * @author: nyc 
 * @since: 2018年4月27日 下午8:53:36 
 * @history:
 */
public class XN631090 extends AProcessor {

    private ISmsOutAO smsOutAO = SpringContextHolder.getBean(ISmsOutAO.class);

    private XN631090Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        smsOutAO.sendSmsCaptcha(req.getMobile(), req.getBizType());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631090Req.class);
        ObjValidater.validateReq(req);
    }

}
