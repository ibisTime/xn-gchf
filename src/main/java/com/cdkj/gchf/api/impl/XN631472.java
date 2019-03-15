package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProjectAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.dto.req.XN631472Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 修改发放薪资可延迟天数（监管端）
 * @author: silver 
 * @since: 2018年7月9日 下午5:18:51 
 * @history:
 */
public class XN631472 extends AProcessor {
    private IProjectAO projectAO = SpringContextHolder
        .getBean(IProjectAO.class);

    private XN631472Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        projectAO.editSalaryDelayDays(req.getProjectCode(),
            StringValidater.toInteger(req.getSalaryDelayDays()));
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631472Req.class);
        ObjValidater.validateReq(req);
    }
}
