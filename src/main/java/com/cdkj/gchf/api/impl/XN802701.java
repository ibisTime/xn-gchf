package com.cdkj.gchf.api.impl;

import org.apache.commons.collections.CollectionUtils;

import com.cdkj.gchf.ao.IChargeAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.dto.req.XN802701Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 批量审批线下充值订单
 * @author: myb858 
 * @since: 2017年5月3日 上午9:24:44 
 * @history:
 */
public class XN802701 extends AProcessor {
    private IChargeAO chargeAO = SpringContextHolder.getBean(IChargeAO.class);

    private XN802701Req req = null;

    /** 
    * @see com.xnjr.base.api.IProcessor#doBusiness()
    */
    @Override
    public synchronized Object doBusiness() throws BizException {
        for (String code : req.getCodeList()) {
            chargeAO.payOrder(code, req.getPayUser(), req.getPayResult(),
                req.getPayNote(), req.getSystemCode());
        }
        return new BooleanRes(true);
    }

    /** 
    * @see com.xnjr.base.api.IProcessor#doCheck(java.lang.String)
    */
    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802701Req.class);
        if (CollectionUtils.isEmpty(req.getCodeList())) {
            throw new BizException("订单列表不能为空");
        }
        StringValidater.validateBlank(req.getPayUser(), req.getPayResult(),
            req.getPayNote(), req.getSystemCode());
    }
}
