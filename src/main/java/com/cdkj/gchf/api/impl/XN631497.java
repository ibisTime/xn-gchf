package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IQueryLogAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631497Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 详情查询记录
 * @author: nyc 
 * @since: 2018年5月21日 下午4:29:35 
 * @history:
 */
public class XN631497 extends AProcessor {

    private IQueryLogAO queryLogAO = SpringContextHolder
        .getBean(IQueryLogAO.class);

    private XN631497Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return queryLogAO.getQueryLog(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631497Req.class);
        ObjValidater.validateReq(req);
    }

}
