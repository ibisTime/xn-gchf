package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IBrandAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN630402Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 修改品牌
 * @author: CYL 
 * @since: 2018年4月24日 下午5:31:42 
 * @history:
 */

public class XN630402 extends AProcessor {
    private IBrandAO brandAO = SpringContextHolder.getBean(IBrandAO.class);

    private XN630402Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        brandAO.editBrand(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630402Req.class);
        ObjValidater.validateReq(req);
    }
}
