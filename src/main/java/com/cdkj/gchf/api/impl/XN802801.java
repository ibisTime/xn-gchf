package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IHLOrderAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.dto.req.XN802801Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 人工调账
 * @author: myb858 
 * @since: 2017年4月24日 下午5:43:53 
 * @history:
 */
public class XN802801 extends AProcessor {
    private IHLOrderAO hlOrderAO = SpringContextHolder
        .getBean(IHLOrderAO.class);

    private XN802801Req req = null;

    @Override
    public synchronized Object doBusiness() throws BizException {
        hlOrderAO.approveOrder(req.getCode(), req.getAdjustResult(),
            req.getAdjustUser(), req.getAdjustNote(), req.getSystemCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802801Req.class);
        StringValidater.validateBlank(req.getCode(), req.getAdjustResult(),
            req.getAdjustUser(), req.getAdjustNote(), req.getSystemCode());
    }
}
