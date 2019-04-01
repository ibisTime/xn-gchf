package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IWorkerAttendanceAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631713Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631713   
 * @Description: 批量导入人员考勤
 * @author: Old3
 * @date:   2019年3月27日 下午2:22:44     
 * @Copyright:
 */
public class XN631713 extends AProcessor {
    private IWorkerAttendanceAO workerAttendanceAO = SpringContextHolder
        .getBean(IWorkerAttendanceAO.class);

    private XN631713Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        workerAttendanceAO.importWorkerAttendanceList(req);
        return new Boolean(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631713Req.class);
        ObjValidater.validateReq(req);
    }

}
