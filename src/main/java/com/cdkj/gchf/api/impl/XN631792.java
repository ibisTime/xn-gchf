package com.cdkj.gchf.api.impl;

import org.web3j.abi.datatypes.Bool;

import com.cdkj.gchf.ao.IWorkerInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631792Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631792   
 * @Description:添加人员实名制信息-联系方式
 * @author: Old3
 * @date:   2019年4月1日 下午2:56:56     
 * @Copyright:
 */
public class XN631792 extends AProcessor {
    private IWorkerInfoAO workerInfoAO = SpringContextHolder
        .getBean(IWorkerInfoAO.class);

    private XN631792Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        workerInfoAO.addWorkerInfoContact(req);
        return new Bool(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631792Req.class);
        ObjValidater.validateReq(req);
    }

}
