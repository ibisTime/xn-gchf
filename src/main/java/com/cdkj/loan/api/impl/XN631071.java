package com.cdkj.loan.api.impl;

import com.cdkj.loan.ao.IUserAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.common.JsonUtil;
import com.cdkj.loan.core.ObjValidater;
import com.cdkj.loan.dto.req.XN631071Req;
import com.cdkj.loan.dto.res.XN631071Res;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

/**
 * 登录
 * @author: nyc 
 * @since: 2018年4月24日 上午11:30:04 
 * @history:
 */
public class XN631071 extends AProcessor {

    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN631071Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN631071Res(userAO.doLogin(req.getLoginName(),
            req.getLoginPwd(), req.getKind()));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631071Req.class);
        ObjValidater.validateReq(req);

    }

}
