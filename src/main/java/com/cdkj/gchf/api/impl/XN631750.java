package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IBankCardInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631750Req;
import com.cdkj.gchf.dto.res.PKCodeRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631750   
 * @Description:添加银行卡
 * @author: Old3
 * @date:   2019年3月22日 上午9:41:05     
 * @Copyright:
 */
public class XN631750 extends AProcessor {
    private IBankCardInfoAO cardInfoAO = SpringContextHolder
        .getBean(IBankCardInfoAO.class);

    private XN631750Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(cardInfoAO.addBankCardInfo(req));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631750Req.class);
        ObjValidater.validateReq(req);
    }

}
