package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IBrandAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN630400Req;
import com.cdkj.gchf.dto.res.PKCodeRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 新增品牌
 * @author: CYL 
 * @since: 2018年4月24日 下午5:34:18 
 * @history:
 */

public class XN630400 extends AProcessor {

    private IBrandAO brandAO = SpringContextHolder.getBean(IBrandAO.class);

    private XN630400Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(brandAO.addBrand(req));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630400Req.class);
        ObjValidater.validateReq(req);
    }

}
