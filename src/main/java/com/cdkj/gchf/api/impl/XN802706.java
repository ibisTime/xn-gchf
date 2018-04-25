package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IChargeAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.dto.req.XN802706Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 详情查询充值订单
 * @author: xieyj 
 * @since: 2017年5月13日 下午7:58:10 
 * @history:
 */
public class XN802706 extends AProcessor {
    private IChargeAO chargeAO = SpringContextHolder.getBean(IChargeAO.class);

    private XN802706Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return chargeAO.getCharge(req.getCode(), req.getSystemCode());
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802706Req.class);
        StringValidater.validateBlank(req.getCode(), req.getSystemCode());

    }

}
