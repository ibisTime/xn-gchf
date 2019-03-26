package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IWorkerInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.domain.WorkerInfo;
import com.cdkj.gchf.dto.req.XN631805Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631807   
 * @Description: 列表查实名认证信息
 * @author: Old3
 * @date:   2019年3月25日 下午4:58:03     
 * @Copyright:
 */
public class XN631807 extends AProcessor {
    private IWorkerInfoAO workerInfoAO = SpringContextHolder
        .getBean(IWorkerInfoAO.class);

    private XN631805Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        req.getOrderColumn();
        WorkerInfo workerInfo = new WorkerInfo();
        workerInfo.setOrder(req.getOrderColumn(), req.getOrderDir());
        return workerInfoAO.queryWorkerInfoList(workerInfo);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631805Req.class);
        ObjValidater.validateReq(req);
    }

}
