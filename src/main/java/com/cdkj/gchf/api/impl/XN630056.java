package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IUserAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN630056Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 重置用户密码 
 * @author: nyc 
 * @since: 2018年4月24日 上午11:30:04 
 * @history:
 */
public class XN630056 extends AProcessor {

    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN630056Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doResetLoginPwdByOss(req.getAdminId(), req.getAdminLoginPwd(),
            req.getUserId(), req.getLoginPwd());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630056Req.class);
        ObjValidater.validateReq(req);

    }

}
