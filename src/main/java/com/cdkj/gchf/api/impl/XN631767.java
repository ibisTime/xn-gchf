package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IBankCardInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631767Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631767   
 * @Description:列表查银行卡
 * @author: Old3
 * @date:   2019年3月22日 下午2:50:30     
 * @Copyright:
 */
public class XN631767 extends AProcessor {
    private IBankCardInfoAO bankCardInfoAO = SpringContextHolder
        .getBean(IBankCardInfoAO.class);

    private XN631767Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        return bankCardInfoAO.queryBankCardInfoList(req);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631767Req.class);
        ObjValidater.validateReq(req);
    }

}
