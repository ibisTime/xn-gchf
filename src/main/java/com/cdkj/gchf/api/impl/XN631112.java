package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IChannelBankAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631112Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 修改银行
 * @author: nyc 
 * @since: 2018年4月27日 下午8:53:36 
 * @history:
 */
public class XN631112 extends AProcessor {

    private IChannelBankAO channelBankAO = SpringContextHolder
        .getBean(IChannelBankAO.class);

    private XN631112Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        channelBankAO.editChannelBank(req.getId(), req.getBankCode(),
            req.getBankName());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631112Req.class);
        ObjValidater.validateReq(req);
    }

}
