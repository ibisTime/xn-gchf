package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IBankCardInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631754Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;
import javax.swing.Spring;

/**
 * 绑定银行卡(H5)
 *
 * @author : old3
 * @since : 2019-06-18 16:43
 */
public class XN631754 extends AProcessor {

    private IBankCardInfoAO bankCardInfoAO = SpringContextHolder.getBean(IBankCardInfoAO.class);

    private XN631754Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        bankCardInfoAO.bindBankCard(req.getWorkerCode(), req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631754Req.class);
        ObjValidater.validateReq(req);
    }
}

    
    