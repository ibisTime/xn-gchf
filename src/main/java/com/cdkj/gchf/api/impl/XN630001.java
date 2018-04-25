package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ISYSRoleAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN630001Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 角色-删除
 * @author: xieyj 
 * @since: 2016年4月17日 上午8:25:51 
 * @history:
 */
public class XN630001 extends AProcessor {
    private ISYSRoleAO sysRoleAO = SpringContextHolder
        .getBean(ISYSRoleAO.class);

    private XN630001Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new BooleanRes(sysRoleAO.dropSYSRole(req.getCode()));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630001Req.class);
        ObjValidater.validateReq(req);
    }
}
