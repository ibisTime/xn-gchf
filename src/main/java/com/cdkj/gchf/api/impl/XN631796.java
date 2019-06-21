package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IWorkerInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631796Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author old3
 * @title: XN631796
 * @description: 修改手持身份证照片
 * @date 2019-05-20 9:49
 */
public class XN631796 extends AProcessor {

    private XN631796Req req = null;

    private IWorkerInfoAO workerInfoAO = SpringContextHolder.getBean(IWorkerInfoAO.class);

    @Override
    public Object doBusiness() throws BizException {
        workerInfoAO.refreshHandIdCardImage(req.getCode(), req.getHandIdCardImageUrl());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631796Req.class);
        ObjValidater.validateReq(req);
    }
}
