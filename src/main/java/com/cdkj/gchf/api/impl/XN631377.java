package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IBcontractAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.dto.req.XN631377Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 详情查询
 * @author: CYL 
 * @since: 2018年4月28日 上午10:57:42 
 * @history:
 */
public class XN631377 extends AProcessor {
    private IBcontractAO bcontractAO = SpringContextHolder
        .getBean(IBcontractAO.class);

    private XN631377Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return bcontractAO.getBcontract(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631377Req.class);
        StringValidater.validateBlank(req.getCode());
    }
}
