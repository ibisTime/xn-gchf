package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IWorkerAttendanceAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631714Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631714   
 * @Description:批量上传人员考勤
 * @author: Old3
 * @date:   2019年3月26日 上午12:34:08     
 * @Copyright:
 */
public class XN631714 extends AProcessor {
    private IWorkerAttendanceAO workerAttendanceAO = SpringContextHolder
        .getBean(IWorkerAttendanceAO.class);

    private XN631714Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        workerAttendanceAO.uploadWorkerAttendanceList(req.getUserId(),
            req.getCodeList());
        return new Boolean(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631714Req.class);
        ObjValidater.validateReq(req);
    }

}
