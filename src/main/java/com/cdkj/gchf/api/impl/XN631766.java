package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IBankCardInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631766Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631766   
 * @Description: 详细查询银行卡信息
 * @author: Old3
 * @date:   2019年3月22日 下午1:33:50     
 * @Copyright:
 */
public class XN631766 extends AProcessor {

    private IBankCardInfoAO bankCardInfoAO = SpringContextHolder
        .getBean(IBankCardInfoAO.class);

    private XN631766Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return bankCardInfoAO.getBankCardInfo(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631766Req.class);
        ObjValidater.validateReq(req);
    }

}
