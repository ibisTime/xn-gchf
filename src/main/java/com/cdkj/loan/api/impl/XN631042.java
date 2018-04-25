package com.cdkj.loan.api.impl;

import com.cdkj.loan.ao.ISYSRoleAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.common.JsonUtil;
import com.cdkj.loan.core.ObjValidater;
import com.cdkj.loan.domain.SYSRole;
import com.cdkj.loan.dto.req.XN631042Req;
import com.cdkj.loan.dto.res.BooleanRes;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

/**
 * 角色-修改
 * @author: xieyj 
 * @since: 2016年4月17日 上午8:26:30 
 * @history:
 */
public class XN631042 extends AProcessor {
    private ISYSRoleAO sysRoleAO = SpringContextHolder
        .getBean(ISYSRoleAO.class);

    private XN631042Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSRole data = new SYSRole();
        data.setCode(req.getCode());
        data.setName(req.getName());
        data.setLevel(req.getLevel());
        data.setUpdater(req.getUpdater());
        data.setRemark(req.getRemark());
        return new BooleanRes(sysRoleAO.editSYSRole(data));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631042Req.class);
        ObjValidater.validateReq(req);
    }
}
