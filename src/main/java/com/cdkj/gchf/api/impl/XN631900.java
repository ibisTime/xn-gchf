package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ICorpBasicinfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631900Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 上传企业信息
 * @author: silver 
 * @since: Mar 15, 2019 4:01:28 PM 
 * @history:
 */
public class XN631900 extends AProcessor {
    private ICorpBasicinfoAO corpBasicinfoAO = SpringContextHolder
        .getBean(ICorpBasicinfoAO.class);

    private XN631900Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        corpBasicinfoAO.uploadCorpBasicinfo(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631900Req.class);
        ObjValidater.validateReq(req);
    }

}
