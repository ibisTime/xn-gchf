package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProgressAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631382Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 工程进度修改
 * @author: CYL 
 * @since: 2018年4月28日 下午4:50:57 
 * @history:
 */
public class XN631382 extends AProcessor {
    private IProgressAO progressAO = SpringContextHolder
        .getBean(IProgressAO.class);

    private XN631382Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        progressAO.editProgress(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631382Req.class);
        ObjValidater.validateReq(req);
    }

}
