package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IWorkerInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631794Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 录入考勤照片
 * @author: silver 
 * @since: May 5, 2019 4:58:02 PM 
 * @history:
 */
public class XN631794 extends AProcessor {
    private IWorkerInfoAO workerInfoAO = SpringContextHolder
        .getBean(IWorkerInfoAO.class);

    private XN631794Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        workerInfoAO.refreshAttendancePicture(req.getCode(),
            req.getAttendancePicture(), req.getUserId());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631794Req.class);
        ObjValidater.validateReq(req);
    }

}
