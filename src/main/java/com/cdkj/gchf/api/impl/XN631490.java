package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IQueryLogAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631490Req;
import com.cdkj.gchf.dto.res.PKCodeRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 添加查询记录
 * @author: nyc 
 * @since: 2018年5月21日 下午4:29:35 
 * @history:
 */
public class XN631490 extends AProcessor {

    private IQueryLogAO queryLogAO = SpringContextHolder
        .getBean(IQueryLogAO.class);

    private XN631490Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(
            queryLogAO.addQueryLog(req.getUserId(), req.getIdNo()));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631490Req.class);
        ObjValidater.validateReq(req);
    }

}
