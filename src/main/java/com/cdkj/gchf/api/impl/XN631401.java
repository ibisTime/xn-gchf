package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ICcontractAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631401Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 删除雇佣合同
 * @author: silver 
 * @since: 2018年8月3日 上午10:01:54 
 * @history:
 */
public class XN631401 extends AProcessor {

    private ICcontractAO ccontractAO = SpringContextHolder
        .getBean(ICcontractAO.class);

    private XN631401Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ccontractAO.dropCcontract(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631401Req.class);
        ObjValidater.validateReq(req);
    }

}
