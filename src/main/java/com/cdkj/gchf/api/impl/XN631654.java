package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ITeamMasterAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631654Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 批量上传班组
 * @author: silver 
 * @since: Mar 25, 2019 5:31:21 PM 
 * @history:
 */
public class XN631654 extends AProcessor {
    private ITeamMasterAO teamMasterAO = SpringContextHolder
        .getBean(ITeamMasterAO.class);

    private XN631654Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        teamMasterAO.uploadTeamMaster(req.getCodeList(), req.getUserId());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631654Req.class);
        ObjValidater.validateReq(req);
    }

}
