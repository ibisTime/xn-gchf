package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IPayRollAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631770Req;
import com.cdkj.gchf.dto.res.PKCodeRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631770   
 * @Description:添加工资单
 * @author: Old3
 * @date:   2019年3月22日 下午3:32:31     
 * @Copyright:
 */
public class XN631770 extends AProcessor {
    private IPayRollAO payRollAO = SpringContextHolder
        .getBean(IPayRollAO.class);

    private XN631770Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(payRollAO.addPayRoll(req));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631770Req.class);
        ObjValidater.validateReq(req);
    }

}
