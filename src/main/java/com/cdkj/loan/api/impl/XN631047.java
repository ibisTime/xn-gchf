package com.cdkj.loan.api.impl;

import com.cdkj.loan.ao.ISYSRoleAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.common.JsonUtil;
import com.cdkj.loan.core.ObjValidater;
import com.cdkj.loan.dto.req.XN631047Req;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

/**
 * 角色-详情
 * @author: xieyj 
 * @since: 2016年4月17日 上午8:26:30 
 * @history:
 */
public class XN631047 extends AProcessor {
    private ISYSRoleAO sysRoleAO = SpringContextHolder
        .getBean(ISYSRoleAO.class);

    private XN631047Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return sysRoleAO.getSYSRole(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631047Req.class);
        ObjValidater.validateReq(req);
    }
}
