package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IWorkerContractAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631686Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631686   
 * @Description:详细查询劳动合同
 * @author: Old3
 * @date:   2019年3月20日 下午7:39:50     
 * @Copyright:
 */
public class XN631686 extends AProcessor {
    private IWorkerContractAO workerContractAO = SpringContextHolder
        .getBean(IWorkerContractAO.class);

    private XN631686Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return workerContractAO.getWorkerContract(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631686Req.class);
        ObjValidater.validateReq(req);
    }

}
