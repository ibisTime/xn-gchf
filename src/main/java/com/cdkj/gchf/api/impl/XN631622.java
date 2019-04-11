package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProjectConfigAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631622Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 编辑项目配置
 * @author: silver 
 * @since: Mar 12, 2019 5:10:46 PM 
 * @history:
 */
public class XN631622 extends AProcessor {
    private IProjectConfigAO projectConfigAO = SpringContextHolder
        .getBean(IProjectConfigAO.class);

    private XN631622Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        projectConfigAO.editProjectConfig(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631622Req.class);
        ObjValidater.validateReq(req);
    }
}
