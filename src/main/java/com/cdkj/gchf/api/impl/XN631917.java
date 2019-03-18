package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IWorkerContractAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631917Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 查询人员合同
 * @author: silver 
 * @since: Mar 15, 2019 4:01:28 PM 
 * @history:
 */
public class XN631917 extends AProcessor {
    private IWorkerContractAO workerContractAO = SpringContextHolder
        .getBean(IWorkerContractAO.class);

    private XN631917Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return workerContractAO.queryWorkerContract(req);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631917Req.class);
        ObjValidater.validateReq(req);
    }

}
