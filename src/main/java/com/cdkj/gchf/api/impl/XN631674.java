package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IWorkerContractAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631674Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631674   
 * @Description: 批量上传劳动合同
 * @author: Old3
 * @date:   2019年3月25日 下午9:57:43     
 * @Copyright:
 */
public class XN631674 extends AProcessor {
    private IWorkerContractAO workerContractAO = SpringContextHolder
        .getBean(IWorkerContractAO.class);

    private XN631674Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        workerContractAO.uploadWorkContractList(req.getUserId(),
            req.getCodeList());
        return new Boolean(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631674Req.class);
        ObjValidater.validateReq(req);
    }

}
