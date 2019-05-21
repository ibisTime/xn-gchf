package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IWorkerInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631795Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * @author old3
 * @title: XN631795
 * @description: ocr识别
 * @date 2019-05-16 14:15
 */
public class XN631795 extends AProcessor {
    private XN631795Req req = null;

    private IWorkerInfoAO workerInfoAO = SpringContextHolder
        .getBean(IWorkerInfoAO.class);

    @Override
    public Object doBusiness() throws BizException {
        return workerInfoAO.addOcrWorkerInfo(req);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631795Req.class);
        ObjValidater.validateReq(req);
    }
}
