package com.cdkj.gchf.api.impl;

import org.springframework.beans.BeanUtils;

import com.cdkj.gchf.ao.IWorkerAttendanceAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.domain.WorkerAttendance;
import com.cdkj.gchf.dto.req.XN631727Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631727   
 * @Description:TODO
 * @author: Old3
 * @date:   2019年3月21日 下午7:56:51     
 * @Copyright:
 */
public class XN631727 extends AProcessor {
    private IWorkerAttendanceAO workerAttendanceAO = SpringContextHolder
        .getBean(IWorkerAttendanceAO.class);

    private XN631727Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        WorkerAttendance condition = new WorkerAttendance();
        BeanUtils.copyProperties(req, condition);
        String column = req.getOrderColumn();
        if (org.apache.commons.lang3.StringUtils.isBlank(column)) {
            column = IWorkerAttendanceAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(req.getOrderColumn(), req.getOrderDir());
        return workerAttendanceAO.queryWorkerAttendanceList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631727Req.class);
        ObjValidater.validateReq(req);
    }

}
