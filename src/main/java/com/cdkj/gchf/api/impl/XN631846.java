package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IWorkerEntryExitRecordAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631846Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 详情查进出场记录
 * @author: silver 
 * @since: May 9, 2019 3:19:06 PM 
 * @history:
 */
public class XN631846 extends AProcessor {

    private IWorkerEntryExitRecordAO workerEntryExitRecordAO = SpringContextHolder
        .getBean(IWorkerEntryExitRecordAO.class);

    private XN631846Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return workerEntryExitRecordAO.getWorkerEntryExitRecord(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631846Req.class);
        ObjValidater.validateReq(req);
    }

}
