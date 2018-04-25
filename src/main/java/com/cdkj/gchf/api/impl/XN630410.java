package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ISeriesAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN630410Req;
import com.cdkj.gchf.dto.res.PKCodeRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 增加车系
 * @author: CYL 
 * @since: 2018年4月24日 下午5:36:08 
 * @history:
 */
public class XN630410 extends AProcessor {

    private ISeriesAO seriesAO = SpringContextHolder.getBean(ISeriesAO.class);

    private XN630410Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(seriesAO.addSeries(req));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630410Req.class);
        ObjValidater.validateReq(req);
    }

}
