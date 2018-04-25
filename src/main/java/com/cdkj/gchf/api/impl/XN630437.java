package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ICarOrderAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN630437Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 详情查询
 * @author: CYL 
 * @since: 2018年4月24日 下午5:40:53 
 * @history:
 */
public class XN630437 extends AProcessor {

    private ICarOrderAO carOderAO = SpringContextHolder
        .getBean(ICarOrderAO.class);

    private XN630437Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return carOderAO.getCarOrder(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630437Req.class);
        ObjValidater.validateReq(req);
    }

}
