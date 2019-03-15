package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ILeaveAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631469Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 详细查询请假明细
 * @author: silver 
 * @since: 2018年7月3日 下午4:33:50 
 * @history:
 */
public class XN631469 extends AProcessor {

    private ILeaveAO leaveAO = SpringContextHolder.getBean(ILeaveAO.class);

    private XN631469Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return leaveAO.getLeave(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631469Req.class);
        ObjValidater.validateReq(req);
    }

}
