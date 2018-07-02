package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IAttendanceAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631390Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 手动上班打卡
 * @author: silver 
 * @since: 2018年6月25日 下午1:54:05 
 * @history:
 */
public class XN631390 extends AProcessor {

    private IAttendanceAO attendanceAO = SpringContextHolder
        .getBean(IAttendanceAO.class);

    private XN631390Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        attendanceAO.startWorkManualClockIn(req.getCode(), DateUtil
            .strToDate(req.getStartDatetime(), DateUtil.DATA_TIME_PATTERN_1));
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631390Req.class);
        ObjValidater.validateReq(req);
    }

}
