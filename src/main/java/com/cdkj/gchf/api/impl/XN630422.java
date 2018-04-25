package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ICarAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN630422Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 修改车型
 * @author: CYL 
 * @since: 2018年4月24日 下午5:38:49 
 * @history:
 */

public class XN630422 extends AProcessor {
    private ICarAO carAO = SpringContextHolder.getBean(ICarAO.class);

    private XN630422Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        carAO.editCar(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630422Req.class);
        ObjValidater.validateReq(req);
    }
}
