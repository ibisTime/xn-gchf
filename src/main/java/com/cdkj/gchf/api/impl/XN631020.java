package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ICompanyAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631020Req;
import com.cdkj.gchf.dto.res.PKCodeRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

public class XN631020 extends AProcessor {

    private ICompanyAO companyAO = SpringContextHolder
        .getBean(ICompanyAO.class);

    private XN631020Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(companyAO.addCompany(req));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631020Req.class);
        ObjValidater.validateReq(req);
    }

}
