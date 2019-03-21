package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProjectWorkerEntryExitHistoryAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631731Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631731   
 * @Description:删除人员进退场
 * @author: Old3
 * @date:   2019年3月21日 下午3:26:36     
 * @Copyright:
 */
public class XN631731 extends AProcessor {
    private IProjectWorkerEntryExitHistoryAO projectWorkerEntryExitHistoryAO = SpringContextHolder
        .getBean(IProjectWorkerEntryExitHistoryAO.class);

    private XN631731Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        projectWorkerEntryExitHistoryAO
            .dropProjectWorkerEntryExitHistory(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631731Req.class);
        ObjValidater.validateReq(req);
    }

}
