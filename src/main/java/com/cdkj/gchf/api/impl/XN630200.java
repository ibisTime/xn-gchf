package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ICUserAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN630200Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * C端用户注册
 * @author: CYL 
 * @since: 2018年4月24日 下午5:32:02 
 * @history:
 */
public class XN630200 extends AProcessor {

    private ICUserAO cuserAO = SpringContextHolder.getBean(ICUserAO.class);

    private XN630200Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return cuserAO.doCaptchaLoginReg(req.getMobile(), req.getSmsCaptcha());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630200Req.class);
        ObjValidater.validateReq(req);
    }

}
