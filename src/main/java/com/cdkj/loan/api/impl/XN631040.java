package com.cdkj.loan.api.impl;

import com.cdkj.loan.ao.ISYSRoleAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.common.JsonUtil;
import com.cdkj.loan.core.ObjValidater;
import com.cdkj.loan.domain.SYSRole;
import com.cdkj.loan.dto.req.XN631040Req;
import com.cdkj.loan.dto.res.PKCodeRes;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

/**
 * 角色-新增
 * @author: xieyj 
 * @since: 2016年4月17日 上午8:25:15 
 * @history:
 */
public class XN631040 extends AProcessor {
    private ISYSRoleAO sysRoleAO = SpringContextHolder
        .getBean(ISYSRoleAO.class);

    private XN631040Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSRole data = new SYSRole();
        data.setName(req.getName());
        data.setLevel(req.getLevel());
        data.setUpdater(req.getUpdater());
        data.setRemark(req.getRemark());
        return new PKCodeRes(sysRoleAO.addSYSRole(data));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631040Req.class);
        ObjValidater.validateReq(req);
    }

}
