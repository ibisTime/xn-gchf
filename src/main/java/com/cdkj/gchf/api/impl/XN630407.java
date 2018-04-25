package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IBrandAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN630407Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 详情查询
 * @author: CYL 
 * @since: 2018年4月20日 下午3:01:12 
 * @history:
 */

public class XN630407 extends AProcessor {

    private IBrandAO brandAO = SpringContextHolder.getBean(IBrandAO.class);

    private XN630407Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return brandAO.getBrand(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630407Req.class);
        ObjValidater.validateReq(req);
    }

}
