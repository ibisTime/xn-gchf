package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ICcontractAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631407Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 详情查合同
 * @author: nyc 
 * @since: 2018年4月29日 下午10:21:06 
 * @history:
 */
public class XN631407 extends AProcessor {

    private ICcontractAO ccontractAO = SpringContextHolder
        .getBean(ICcontractAO.class);

    private XN631407Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        return ccontractAO.getCcontract(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631407Req.class);
        ObjValidater.validateReq(req);
    }

}
