package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IWorkerAttendanceAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631711Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631711   
 * @Description: 删除人员考勤
 * @author: Old3
 * @date:   2019年3月21日 下午6:03:31     
 * @Copyright:
 */
public class XN631711 extends AProcessor {
    private IWorkerAttendanceAO workerAttendanceAO = SpringContextHolder
        .getBean(IWorkerAttendanceAO.class);

    private XN631711Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        workerAttendanceAO.dropWorkerAttendance(req.getCodeList());
        return new Boolean(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631711Req.class);
        ObjValidater.validateReq(req);
    }

}
