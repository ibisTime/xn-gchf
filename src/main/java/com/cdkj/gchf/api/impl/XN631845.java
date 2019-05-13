package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cdkj.gchf.ao.IEquipmentWorkerAO;
import com.cdkj.gchf.ao.IWorkerEntryExitRecordAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.WorkerEntryExitRecord;
import com.cdkj.gchf.dto.req.XN631845Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 分页查进出场记录
 * @author: silver 
 * @since: May 9, 2019 3:19:06 PM 
 * @history:
 */
public class XN631845 extends AProcessor {

    private IWorkerEntryExitRecordAO workerEntryExitRecordAO = SpringContextHolder
        .getBean(IWorkerEntryExitRecordAO.class);

    private XN631845Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        WorkerEntryExitRecord workerEntryExitRecord = new WorkerEntryExitRecord();
        BeanUtils.copyProperties(req, workerEntryExitRecord);

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        String order = req.getOrderColumn();
        if (StringUtils.isBlank(order)) {
            order = IEquipmentWorkerAO.DEFAULT_ORDER_COLUMN;
        }
        workerEntryExitRecord.setOrder(order, req.getOrderDir());

        return workerEntryExitRecordAO.queryWorkerEntryExitRecordPage(
            req.getUserId(), start, limit, workerEntryExitRecord);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631845Req.class);
        ObjValidater.validateReq(req);
    }

}
