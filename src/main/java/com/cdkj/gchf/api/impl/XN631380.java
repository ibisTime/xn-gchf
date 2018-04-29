package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProgressAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631380Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 工程进度录入
 * @author: CYL 
 * @since: 2018年4月28日 下午4:50:43 
 * @history:
 */
public class XN631380 extends AProcessor {

    private IProgressAO progressAO = SpringContextHolder
        .getBean(IProgressAO.class);

    private XN631380Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return progressAO.addProgress(req);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631380Req.class);
        ObjValidater.validateReq(req);
    }

}
