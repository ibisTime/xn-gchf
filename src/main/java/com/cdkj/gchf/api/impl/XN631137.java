package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cdkj.gchf.ao.IOperateLogAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.domain.OperateLog;
import com.cdkj.gchf.dto.req.XN631137Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

public class XN631137 extends AProcessor {
    private IOperateLogAO operateLogAO = SpringContextHolder
        .getBean(IOperateLogAO.class);

    private XN631137Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        OperateLog operateLog = new OperateLog();
        BeanUtils.copyProperties(req, operateLog);
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IOperateLogAO.DEFAULT_ORDER_COLUMN;
        }
        operateLog.setOrder(column, req.getOrderDir());
        return operateLogAO.queryOperateLogList(operateLog);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631137Req.class);
        ObjValidater.validateReq(req);
    }

}
