package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IPayRollAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631786Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631786   
 * @Description:详细查工资单
 * @author: Old3
 * @date:   2019年3月23日 下午2:43:47     
 * @Copyright:
 */
public class XN631786 extends AProcessor {
    private IPayRollAO payRollAO = SpringContextHolder
        .getBean(IPayRollAO.class);

    private XN631786Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return payRollAO.getPayRoll(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631786Req.class);
        ObjValidater.validateReq(req);
    }

}
