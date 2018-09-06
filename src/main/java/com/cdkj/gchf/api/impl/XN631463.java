package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IEmployAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631463Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 修改入职信息
 * @author: silver 
 * @since: 2018年7月13日 上午11:46:38 
 * @history:
 */
public class XN631463 extends AProcessor {

    private IEmployAO employAO = SpringContextHolder.getBean(IEmployAO.class);

    private XN631463Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        employAO.reEmploy(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631463Req.class);
        ObjValidater.validateReq(req);
    }

}
