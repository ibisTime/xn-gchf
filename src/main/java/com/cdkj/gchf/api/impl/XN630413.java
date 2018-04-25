package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ISeriesAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN630413Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 车系上架
 * @author: CYL 
 * @since: 2018年4月24日 下午5:36:39 
 * @history:
 */

public class XN630413 extends AProcessor {

    private ISeriesAO seriesAO = SpringContextHolder.getBean(ISeriesAO.class);

    private XN630413Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        seriesAO.upSeries(req.getCode(), req.getLocation(), req.getOrderNo(),
            req.getUpdater(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630413Req.class);
        ObjValidater.validateReq(req);

    }

}
