package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IBankCardAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631420Req;
import com.cdkj.gchf.dto.res.PKCodeRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 添加工资卡
 * @author: silver 
 * @since: 2018年7月12日 上午10:27:15 
 * @history:
 */
public class XN631420 extends AProcessor {

    private IBankCardAO bankCardAO = SpringContextHolder
        .getBean(IBankCardAO.class);

    private XN631420Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(bankCardAO.addBankCard(req));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631420Req.class);
        ObjValidater.validateReq(req);
    }

}
