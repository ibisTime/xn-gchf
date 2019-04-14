package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IWorkerInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631808Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631807   
 * @Description: 身份证查实名认证信息
 * @author: Old3
 * @date:   2019年3月25日 下午4:58:03     
 * @Copyright:
 */
public class XN631808 extends AProcessor {
    private IWorkerInfoAO workerInfoAO = SpringContextHolder
        .getBean(IWorkerInfoAO.class);

    private XN631808Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return workerInfoAO.getWorkerInfoByIdCardNumber(req.getIdCardNumber());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631808Req.class);
        ObjValidater.validateReq(req);
    }

}
