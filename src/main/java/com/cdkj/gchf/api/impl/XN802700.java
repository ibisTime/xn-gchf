package com.cdkj.gchf.api.impl;

import java.math.BigDecimal;

import com.cdkj.gchf.ao.IChargeAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.dto.req.XN802700Req;
import com.cdkj.gchf.dto.res.PKCodeRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 线下充值申请
 * @author: myb858 
 * @since: 2017年5月3日 上午9:23:51 
 * @history:
 */
public class XN802700 extends AProcessor {
    private IChargeAO chargeAO = SpringContextHolder.getBean(IChargeAO.class);

    private XN802700Req req = null;

    /** 
    * @see com.xnjr.base.api.IProcessor#doBusiness()
    */
    @Override
    public synchronized Object doBusiness() throws BizException {
        BigDecimal amount = StringValidater.toBigDecimal(req.getAmount());
        String code = chargeAO.applyOrder(req.getAccountNumber(), amount,
            req.getPayCardInfo(), req.getPayCardNo(), req.getApplyUser(),
            req.getApplyNote());
        return new PKCodeRes(code);
    }

    /** 
    * @see com.xnjr.base.api.IProcessor#doCheck(java.lang.String)
    */
    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802700Req.class);
        StringValidater.validateBlank(req.getAccountNumber(),
            req.getApplyUser());
        StringValidater.validateAmount(req.getAmount());
    }
}
