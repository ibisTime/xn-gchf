package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ISeriesAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN630412Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 修改车系
 * @author: CYL 
 * @since: 2018年4月24日 下午5:36:25 
 * @history:
 */

public class XN630412 extends AProcessor {

    private ISeriesAO seriesAO = SpringContextHolder.getBean(ISeriesAO.class);

    private XN630412Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        seriesAO.editSeries(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630412Req.class);
        ObjValidater.validateReq(req);
    }

}
