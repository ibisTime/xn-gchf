package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProjectWorkerAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631912Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 修改项目人员
 * @author: silver 
 * @since: Mar 15, 2019 4:01:28 PM 
 * @history:
 */
public class XN631912 extends AProcessor {
    private IProjectWorkerAO projectWorkerAO = SpringContextHolder
        .getBean(IProjectWorkerAO.class);

    private XN631912Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        projectWorkerAO.updateProjectWorker(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631912Req.class);
        ObjValidater.validateReq(req);
    }

}
