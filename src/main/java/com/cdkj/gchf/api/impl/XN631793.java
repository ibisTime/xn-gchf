package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IWorkerInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631793Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631793   
 * @Description: 重新建档实名制信息
 * @author: Old3
 * @date:   2019年4月3日 下午7:04:19     
 * @Copyright:
 */
public class XN631793 extends AProcessor {
    private IWorkerInfoAO workerInfoAO = SpringContextHolder
        .getBean(IWorkerInfoAO.class);

    private XN631793Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        workerInfoAO.readdWorkerInfo(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631793Req.class);
        ObjValidater.validateReq(req);
    }

}
