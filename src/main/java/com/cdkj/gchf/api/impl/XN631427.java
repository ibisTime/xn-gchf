package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IBankCardAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631427Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 详情查工资卡
 * @author: nyc 
 * @since: 2018年5月3日 下午7:37:02 
 * @history:
 */
public class XN631427 extends AProcessor {

    private IBankCardAO bankCardAO = SpringContextHolder
        .getBean(IBankCardAO.class);

    private XN631427Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return bankCardAO.getBankCard(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631427Req.class);
        ObjValidater.validateReq(req);
    }

}
