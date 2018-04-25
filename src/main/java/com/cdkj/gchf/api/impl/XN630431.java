package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ICarOrderAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN630431Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 处理
 * @author: CYL 
 * @since: 2018年4月23日 上午9:50:53 
 * @history:
 */

public class XN630431 extends AProcessor {

    private ICarOrderAO carOrderAO = SpringContextHolder
        .getBean(ICarOrderAO.class);

    private XN630431Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        carOrderAO.editCarOrder(req.getCode(), req.getResult(),
            req.getHandler(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630431Req.class);
        ObjValidater.validateReq(req);

    }

}
