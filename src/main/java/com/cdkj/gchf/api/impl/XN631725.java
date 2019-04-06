package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cdkj.gchf.ao.IWorkerAttendanceAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.WorkerAttendance;
import com.cdkj.gchf.dto.req.XN631725Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631725   
 * @Description:分页查人员考勤
 * @author: Old3
 * @date:   2019年3月21日 下午7:52:48     
 * @Copyright:
 */
public class XN631725 extends AProcessor {
    private IWorkerAttendanceAO workerAttendanceAO = SpringContextHolder
        .getBean(IWorkerAttendanceAO.class);

    private XN631725Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        WorkerAttendance condition = new WorkerAttendance();
        BeanUtils.copyProperties(req, condition);
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getStart());
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IWorkerAttendanceAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        return workerAttendanceAO.queryWorkerAttendancePage(start, limit,
            condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631725Req.class);
        ObjValidater.validateReq(req);
    }
}
