package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IWorkerInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631796Req;
import com.cdkj.gchf.dto.req.XN631797Req;
import com.cdkj.gchf.dto.res.PKCodeRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * @author old3
 * @title: XN631797
 * @description: H5 基本信息录入
 * @date 2019-05-20 10:10
 */
public class XN631797 extends AProcessor {
    private XN631797Req req = null;

    private IWorkerInfoAO workerInfoAO = SpringContextHolder.getBean(IWorkerInfoAO.class);

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(workerInfoAO.refreshWorkerInfoH5(req));
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631797Req.class);
        ObjValidater.validateReq(req);
    }
}
