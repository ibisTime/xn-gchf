package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IEmployAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631467Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 详情查务雇佣人员
 * @author: nyc 
 * @since: 2018年4月29日 下午7:32:21 
 * @history:
 */
public class XN631467 extends AProcessor {

    private IEmployAO employAO = SpringContextHolder.getBean(IEmployAO.class);

    private XN631467Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return employAO.getEmploy(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631467Req.class);
        ObjValidater.validateReq(req);
    }

}
