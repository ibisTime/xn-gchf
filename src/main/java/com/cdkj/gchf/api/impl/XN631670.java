package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IWorkerContractAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631670Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631670   
 * @Description:添加劳动合同
 * @author: Old3
 * @date:   2019年3月21日 上午10:58:04     
 * @Copyright:
 */
public class XN631670 extends AProcessor {
    private IWorkerContractAO workerContractAO = SpringContextHolder
        .getBean(IWorkerContractAO.class);

    private XN631670Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return workerContractAO.addWorkerContract(req);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631670Req.class);
        ObjValidater.validateReq(req);
    }

}
