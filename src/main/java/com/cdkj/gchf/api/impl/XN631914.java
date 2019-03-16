package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProjectWorkerEntryExitHistoryAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631914Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 上传人员进退场
 * @author: silver 
 * @since: Mar 15, 2019 4:01:28 PM 
 * @history:
 */
public class XN631914 extends AProcessor {
    private IProjectWorkerEntryExitHistoryAO projectWorkerEntryExitHistoryAO = SpringContextHolder
        .getBean(IProjectWorkerEntryExitHistoryAO.class);

    private XN631914Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        projectWorkerEntryExitHistoryAO
            .uploadProjectWorkerEntryExitHistory(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631914Req.class);
        ObjValidater.validateReq(req);
    }

}
