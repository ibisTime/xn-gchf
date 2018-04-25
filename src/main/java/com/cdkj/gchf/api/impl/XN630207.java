package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ICUserAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN630207Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 详情查询
 * @author: CYL 
 * @since: 2018年4月24日 下午5:33:59 
 * @history:
 */
public class XN630207 extends AProcessor {

    private ICUserAO cuserAO = SpringContextHolder.getBean(ICUserAO.class);

    private XN630207Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return cuserAO.getCUser(req.getUserId());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630207Req.class);
        ObjValidater.validateReq(req);
    }

}
