package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IBankCardAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631428Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 员工工资卡是否存在
 * @author: silver 
 * @since: 2018年7月12日 上午10:40:30 
 * @history:
 */
public class XN631428 extends AProcessor {

    private IBankCardAO bankCardAO = SpringContextHolder
        .getBean(IBankCardAO.class);

    private XN631428Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        if (bankCardAO.isBankCardExist(req.getStaffCode(),
            req.getCompanyCode())) {
            return new BooleanRes(true);
        } else {
            return new BooleanRes(false);
        }
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631428Req.class);
        ObjValidater.validateReq(req);
    }

}
