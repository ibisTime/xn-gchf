package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ICompanyAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631021Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

public class XN631021 extends AProcessor {
    private ICompanyAO companyAO = SpringContextHolder
        .getBean(ICompanyAO.class);

    private XN631021Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        companyAO.deleteCompany(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631021Req.class);
        ObjValidater.validateReq(req);
    }

}
