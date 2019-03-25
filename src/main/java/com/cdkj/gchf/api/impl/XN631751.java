package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IBankCardInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631651Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631751   
 * @Description:删除银行卡信息
 * @author: Old3
 * @date:   2019年3月22日 上午11:56:11     
 * @Copyright:
 */
public class XN631751 extends AProcessor {
    private IBankCardInfoAO bankCardInfoAO = SpringContextHolder
        .getBean(IBankCardInfoAO.class);

    private XN631651Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        bankCardInfoAO.dropBankCardInfo(req.getCode());
        return new Boolean(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631651Req.class);
        ObjValidater.validateReq(req);
    }

}
