package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IBcontractAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631372Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 承包商合同修改
 * @author: CYL 
 * @since: 2018年4月28日 下午4:49:43 
 * @history:
 */
public class XN631372 extends AProcessor {
    private IBcontractAO bcontractAO = SpringContextHolder
        .getBean(IBcontractAO.class);

    private XN631372Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        bcontractAO.editBcontract(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631372Req.class);
        ObjValidater.validateReq(req);
    }

}
