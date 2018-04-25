package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ICompanyAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631027Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 菜单-详情
 * @author: xieyj 
 * @since: 2016年5月16日 下午9:39:48 
 * @history:
 */
public class XN631027 extends AProcessor {
    private ICompanyAO companyAO = SpringContextHolder
        .getBean(ICompanyAO.class);

    private XN631027Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return companyAO.getCompany(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631027Req.class);
        ObjValidater.validateReq(req);
    }
}
