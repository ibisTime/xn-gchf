package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ICcontractAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631402Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 修改合同
 * @author: nyc 
 * @since: 2018年4月29日 下午10:21:06 
 * @history:
 */
public class XN631402 extends AProcessor {

    private ICcontractAO ccontractAO = SpringContextHolder
        .getBean(ICcontractAO.class);

    private XN631402Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ccontractAO.editCcontract(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631402Req.class);
        ObjValidater.validateReq(req);
    }

}
