package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProjectAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631618Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * @author old3
 * @title: XN631618
 * @description: 查询Led相关项目信息
 * @date 2019-05-20 20:26
 */
public class XN631618 extends AProcessor {

    private XN631618Req req = null;

    private IProjectAO projectAO = SpringContextHolder.getBean(IProjectAO.class);

    @Override
    public Object doBusiness() throws BizException {
        return projectAO.queryProjectInfo_led(req.getUserId());
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631618Req.class);
        ObjValidater.validateReq(req);
    }
}
