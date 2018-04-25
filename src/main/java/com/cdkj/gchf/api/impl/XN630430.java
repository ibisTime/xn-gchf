package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ICarOrderAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN630430Req;
import com.cdkj.gchf.dto.res.PKCodeRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 申请
 * @author: CYL 
 * @since: 2018年4月23日 下午4:26:43 
 * @history:
 */

public class XN630430 extends AProcessor {

    private ICarOrderAO carOrderAO = SpringContextHolder
        .getBean(ICarOrderAO.class);

    private XN630430Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(carOrderAO.addCarOrder(req));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630430Req.class);
        ObjValidater.validateReq(req);
    }

}
