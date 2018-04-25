package com.cdkj.loan.api.impl;

import org.apache.commons.collections.CollectionUtils;

import com.cdkj.loan.ao.ISYSMenuRoleAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.common.JsonUtil;
import com.cdkj.loan.core.ObjValidater;
import com.cdkj.loan.domain.SYSMenuRole;
import com.cdkj.loan.dto.req.XN631050Req;
import com.cdkj.loan.dto.res.BooleanRes;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

/**
 * 菜单角色-增加菜单角色
 * @author: xieyj 
 * @since: 2016年4月17日 上午8:26:30 
 * @history:
 */
public class XN631050 extends AProcessor {
    private ISYSMenuRoleAO sysMenuRoleAO = SpringContextHolder
        .getBean(ISYSMenuRoleAO.class);

    private XN631050Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSMenuRole data = new SYSMenuRole();
        data.setRoleCode(req.getRoleCode());
        data.setMenuCodeList(req.getMenuCodeList());
        data.setUpdater(req.getUpdater());
        data.setRemark(req.getRemark());
        sysMenuRoleAO.addSYSMenuRole(data);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631050Req.class);
        ObjValidater.validateReq(req);
        if (CollectionUtils.isEmpty(req.getMenuCodeList())) {
            throw new BizException("xnlh4000", "菜单列表不能为空");
        }
    }
}
