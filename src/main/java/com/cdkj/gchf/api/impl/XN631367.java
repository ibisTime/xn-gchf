package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ICompanyCardAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631367Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 详情查公司账户
 * @author: nyc 
 * @since: 2018年4月27日 下午9:46:22 
 * @history:
 */
public class XN631367 extends AProcessor {

    private ICompanyCardAO companyCardAO = SpringContextHolder
        .getBean(ICompanyCardAO.class);

    private XN631367Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return companyCardAO.getCompanyCard(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631367Req.class);
        ObjValidater.validateReq(req);
    }

}
