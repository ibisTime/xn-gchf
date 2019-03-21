package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProjectWorkerEntryExitHistoryAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631746Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631746   
 * @Description:详细查询进退场人员
 * @author: Old3
 * @date:   2019年3月21日 下午4:22:50     
 * @Copyright:
 */
public class XN631746 extends AProcessor {
    private IProjectWorkerEntryExitHistoryAO projectWorkerEntryExitHistoryAO = SpringContextHolder
        .getBean(IProjectWorkerEntryExitHistoryAO.class);

    private XN631746Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return projectWorkerEntryExitHistoryAO
            .queryProjectWorkerEntryExitHistory(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631746Req.class);
        ObjValidater.validateReq(req);
    }

}
