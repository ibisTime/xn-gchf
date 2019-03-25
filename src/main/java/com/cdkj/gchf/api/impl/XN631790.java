package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IWorkerInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631790Req;
import com.cdkj.gchf.dto.res.PKCodeRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631790   
 * @Description:添加实名认证信息
 * @author: Old3
 * @date:   2019年3月25日 下午3:02:28     
 * @Copyright:
 */
public class XN631790 extends AProcessor {
    private IWorkerInfoAO payRollAO = SpringContextHolder
        .getBean(IWorkerInfoAO.class);

    private XN631790Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(payRollAO.addWorkerInfo(req));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631790Req.class);
        ObjValidater.validateReq(req);
    }

}
