package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ISeriesAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN630417Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 详情查询
 * @author: CYL 
 * @since: 2018年4月24日 下午5:38:13 
 * @history:
 */
public class XN630417 extends AProcessor {

    private ISeriesAO seriesAO = SpringContextHolder.getBean(ISeriesAO.class);

    private XN630417Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return seriesAO.getSeries(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630417Req.class);
        ObjValidater.validateReq(req);
    }

}
