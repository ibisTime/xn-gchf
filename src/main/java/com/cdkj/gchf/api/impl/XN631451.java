package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ISalaryLogAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631451Req;
import com.cdkj.gchf.dto.res.PKCodeRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 异常转为正常
 * @author: nyc 
 * @since: 2018年5月1日 上午11:48:15 
 * @history:
 */
public class XN631451 extends AProcessor {

    private ISalaryLogAO salaryLogAO = SpringContextHolder
        .getBean(ISalaryLogAO.class);

    private XN631451Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(salaryLogAO.changeToNormal(req.getSalaryCode(),
            req.getHandler(), req.getHandleNote()));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631451Req.class);
        ObjValidater.validateReq(req);
    }

}
