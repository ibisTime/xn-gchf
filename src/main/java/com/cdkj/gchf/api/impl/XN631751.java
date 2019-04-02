package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IBankCardInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631751Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631751   
 * @Description: 启用 弃用 银行卡
 * @author: Old3
 * @date:   2019年3月22日 上午11:56:11     
 * @Copyright:
 */
public class XN631751 extends AProcessor {
    private IBankCardInfoAO bankCardInfoAO = SpringContextHolder
        .getBean(IBankCardInfoAO.class);

    private XN631751Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        bankCardInfoAO.changeBankCardStatus(req);
        return new Boolean(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631751Req.class);
        ObjValidater.validateReq(req);
    }

}
