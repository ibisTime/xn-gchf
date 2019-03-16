package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ICorpBasicinfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631901Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 查询企业信息
 * @author: silver 
 * @since: Mar 15, 2019 4:01:28 PM 
 * @history:
 */
public class XN631901 extends AProcessor {
    private ICorpBasicinfoAO corpBasicinfoAO = SpringContextHolder
        .getBean(ICorpBasicinfoAO.class);

    private XN631901Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return corpBasicinfoAO.queryCorpBasicinfo(req);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631901Req.class);
        ObjValidater.validateReq(req);
    }

}
