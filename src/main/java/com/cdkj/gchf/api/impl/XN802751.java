package com.cdkj.gchf.api.impl;

import java.math.BigDecimal;

import com.cdkj.gchf.ao.IWithdrawAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.dto.req.XN802751Req;
import com.cdkj.gchf.dto.res.PKCodeRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 线下取现申请(front/oss 无需交易密码)
 * @author: myb858 
 * @since: 2017年4月24日 下午8:00:31 
 * @history:
 */
public class XN802751 extends AProcessor {

    private IWithdrawAO withdrawAO = SpringContextHolder
        .getBean(IWithdrawAO.class);

    private XN802751Req req = null;

    @Override
    public synchronized Object doBusiness() throws BizException {
        BigDecimal amount = StringValidater.toBigDecimal(req.getAmount());
        String code = withdrawAO.applyOrder(req.getAccountNumber(), amount,
            req.getPayCardInfo(), req.getPayCardNo(), req.getApplyUser(),
            req.getApplyNote());
        return new PKCodeRes(code);
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802751Req.class);
        StringValidater.validateBlank(req.getAccountNumber(),
            req.getPayCardInfo(), req.getPayCardNo(), req.getApplyUser());
        StringValidater.validateAmount(req.getAmount());
    }
}
