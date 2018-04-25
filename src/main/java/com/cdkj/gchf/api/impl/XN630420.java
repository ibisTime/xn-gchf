package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ICarAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN630420Req;
import com.cdkj.gchf.dto.res.PKCodeRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 新增车型
 * @author: CYL 
 * @since: 2018年4月23日 下午4:26:43 
 * @history:
 */

public class XN630420 extends AProcessor {

    private ICarAO carAO = SpringContextHolder.getBean(ICarAO.class);

    private XN630420Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(carAO.addCar(req));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630420Req.class);
        ObjValidater.validateReq(req);
    }

}
