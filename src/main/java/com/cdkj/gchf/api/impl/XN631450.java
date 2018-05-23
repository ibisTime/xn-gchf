package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ISalaryLogAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631450Req;
import com.cdkj.gchf.dto.res.PKCodeRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 处理异常
 * @author: nyc 
 * @since: 2018年5月1日 上午11:48:15 
 * @history:
 */
public class XN631450 extends AProcessor {

    private ISalaryLogAO salaryLogAO = SpringContextHolder
        .getBean(ISalaryLogAO.class);

    private XN631450Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(salaryLogAO.addsalaryLog(req.getSalaryCode(),
            req.getHandler(), req.getHandleNote()));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631450Req.class);
        ObjValidater.validateReq(req);
    }

}
