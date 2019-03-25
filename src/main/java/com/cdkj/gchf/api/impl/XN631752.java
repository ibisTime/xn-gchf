package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IBankCardInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631752Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631752   
 * @Description:修改银行卡信息
 * @author: Old3
 * @date:   2019年3月22日 下午3:10:35     
 * @Copyright:
 */
public class XN631752 extends AProcessor {
    private IBankCardInfoAO cardInfoAO = SpringContextHolder
        .getBean(IBankCardInfoAO.class);

    private XN631752Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        cardInfoAO.editBankCardInfo(req);
        return new Boolean(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631752Req.class);
        ObjValidater.validateReq(req);
    }

}
