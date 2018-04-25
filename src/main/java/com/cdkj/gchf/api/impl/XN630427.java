package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ICarAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN630427Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 详情查询
 * @author: CYL 
 * @since: 2018年4月24日 下午5:40:05 
 * @history:
 */
public class XN630427 extends AProcessor {

    private ICarAO carAO = SpringContextHolder.getBean(ICarAO.class);

    private XN630427Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return carAO.getCar(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630427Req.class);
        ObjValidater.validateReq(req);
    }

}
