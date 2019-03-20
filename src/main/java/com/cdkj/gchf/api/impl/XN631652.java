package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ITeamMasterAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631652Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

public class XN631652 extends AProcessor {
    private ITeamMasterAO teamMasterAO = SpringContextHolder
        .getBean(ITeamMasterAO.class);

    private XN631652Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        teamMasterAO.editTeamMaster(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631652Req.class);
        ObjValidater.validateReq(req);
    }

}
