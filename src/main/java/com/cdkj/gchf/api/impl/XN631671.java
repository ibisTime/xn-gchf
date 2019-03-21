package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProjectWorkerEntryExitHistoryAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631671Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631671   
 * @Description:删除劳动合同
 * @author: Old3
 * @date:   2019年3月20日 下午7:20:10     
 * @Copyright:
 */
public class XN631671 extends AProcessor {
    private IProjectWorkerEntryExitHistoryAO projectWorkerEntryExitHistoryAO = SpringContextHolder
        .getBean(IProjectWorkerEntryExitHistoryAO.class);

    private XN631671Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        projectWorkerEntryExitHistoryAO
            .dropProjectWorkerEntryExitHistory(req.getCode());
        return new Boolean(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631671Req.class);
        ObjValidater.validateReq(req);
    }

}
