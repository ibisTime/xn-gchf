package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IUserAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN630050Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 验证手机号是否存在
 * @author: nyc 
 * @since: 2018年4月24日 上午11:30:04 
 * @history:
 */
public class XN630050 extends AProcessor {

    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN630050Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doCheckMobile(req.getMobile());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630050Req.class);
        ObjValidater.validateReq(req);

    }

}
