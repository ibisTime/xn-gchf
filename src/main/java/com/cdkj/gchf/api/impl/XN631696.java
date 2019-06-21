package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProjectWorkerAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631696Req;
import com.cdkj.gchf.dto.res.PKCodeRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author old3
 * @title: XN631696
 * @description: TODO
 * @date 2019-05-20 10:53
 */
public class XN631696 extends AProcessor {

    private XN631696Req req = null;

    private IProjectWorkerAO projectWorkerAO = SpringContextHolder.getBean(IProjectWorkerAO.class);

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(projectWorkerAO.addProjectWorker(req));
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631696Req.class);
        ObjValidater.validateReq(req);
    }
}
