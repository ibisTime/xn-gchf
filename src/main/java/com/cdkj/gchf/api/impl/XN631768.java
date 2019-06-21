package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IWorkerInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631768Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * @author old3
 * @title: XN631768
 * @description: 列表查实名制人员银行卡
 * @date 2019-05-21 17:54
 */
public class XN631768 extends AProcessor {

    private IWorkerInfoAO workerInfoAO = SpringContextHolder.getBean(IWorkerInfoAO.class);
    private XN631768Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return workerInfoAO.selectWorkerInfoByWorkerCode(req.getWorkerCode());
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631768Req.class);
        ObjValidater.validateReq(req);
    }
}
