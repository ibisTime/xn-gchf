package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IUserAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631080Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : old3
 * @since : 2019-05-23 17:21
 */
public class XN631080 extends AProcessor {

    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);
    private XN631080Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doResetLoginPwd(req.getMobile(), req.getSmsCaptcha(), req.getNewLoginPwd());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631080Req.class);
        ObjValidater.validateReq(req);
    }
}

    
    