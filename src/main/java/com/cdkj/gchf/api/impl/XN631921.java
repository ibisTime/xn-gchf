package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IPayRollAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631921Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 上传人员工资
 * @author: silver 
 * @since: Mar 15, 2019 4:01:28 PM 
 * @history:
 */
public class XN631921 extends AProcessor {
    private IPayRollAO payRollAO = SpringContextHolder
        .getBean(IPayRollAO.class);

    private XN631921Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return payRollAO.queryPayRoll(req);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631921Req.class);
        ObjValidater.validateReq(req);
    }

}
