package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IWorkerAttendanceAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631918Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 上传人员考勤
 * @author: silver 
 * @since: Mar 15, 2019 4:01:28 PM 
 * @history:
 */
public class XN631918 extends AProcessor {
    private IWorkerAttendanceAO workerAttendanceAO = SpringContextHolder
        .getBean(IWorkerAttendanceAO.class);

    private XN631918Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        workerAttendanceAO.uploadWorkerAttendance(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631918Req.class);
        ObjValidater.validateReq(req);
    }

}
