package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IPayRollAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631772Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631772   
 * @Description:修改工资单
 * @author: Old3
 * @date:   2019年3月23日 下午3:29:09     
 * @Copyright:
 */
public class XN631772 extends AProcessor {
    private IPayRollAO payRollAO = SpringContextHolder
        .getBean(IPayRollAO.class);

    private XN631772Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        payRollAO.editPayRoll(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631772Req.class);
        ObjValidater.validateReq(req);
    }

}
