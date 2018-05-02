package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProjectAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631358Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 详情查项目
 * @author: xieyj 
 * @since: 2016年4月17日 上午8:26:30 
 * @history:
 */
public class XN631358 extends AProcessor {
    private IProjectAO projectAO = SpringContextHolder
        .getBean(IProjectAO.class);

    private XN631358Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return projectAO.getProject(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631358Req.class);
        ObjValidater.validateReq(req);
    }
}
