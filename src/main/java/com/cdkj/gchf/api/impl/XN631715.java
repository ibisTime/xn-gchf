package com.cdkj.gchf.api.impl;

import java.util.Date;

import com.cdkj.gchf.ao.IWorkerAttendanceAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631715Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 批量生成考勤
 * @author: silver 
 * @since: Apr 14, 2019 3:17:18 PM 
 * @history:
 */
public class XN631715 extends AProcessor {
    private IWorkerAttendanceAO workerAttendanceAO = SpringContextHolder
        .getBean(IWorkerAttendanceAO.class);

    private XN631715Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Date startDatetime = DateUtil.strToDate(req.getStartDatetime(),
            DateUtil.DATA_TIME_PATTERN_1);
        Date endDatetime = DateUtil.strToDate(req.getEndDatetime(),
            DateUtil.DATA_TIME_PATTERN_1);
        workerAttendanceAO.batchCreateAttandance(req.getProjectCode(),
            req.getDirection(), startDatetime, endDatetime);
        return new Boolean(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631715Req.class);
        ObjValidater.validateReq(req);
    }

}
