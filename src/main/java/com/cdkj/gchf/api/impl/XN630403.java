package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IBrandAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN630403Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 品牌上架
 * @author: CYL 
 * @since: 2018年4月23日 上午9:50:53 
 * @history:
 */

public class XN630403 extends AProcessor {

    private IBrandAO brandAO = SpringContextHolder.getBean(IBrandAO.class);

    private XN630403Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        brandAO.upBrand(req.getCode(), req.getUpdater(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630403Req.class);
        ObjValidater.validateReq(req);

    }

}
