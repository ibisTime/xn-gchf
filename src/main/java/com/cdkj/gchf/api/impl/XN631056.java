package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.ISYSMenuRoleAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.domain.SYSMenuRole;
import com.cdkj.gchf.dto.req.XN631056Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 菜单角色-查询菜单列表
 * @author: xieyj 
 * @since: 2016年4月17日 上午8:26:30 
 * @history:
 */
public class XN631056 extends AProcessor {
    private ISYSMenuRoleAO sysMenuRoleAO = SpringContextHolder
        .getBean(ISYSMenuRoleAO.class);

    private XN631056Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSMenuRole condition = new SYSMenuRole();
        condition.setRoleCode(req.getRoleCode());
        condition.setParentCode(req.getParentCode());
        condition.setType(req.getType());
        condition.setSystemCode(req.getSystemCode());
        String column = req.getOrderColumn();

        if (StringUtils.isEmpty(column)) {
            column = ISYSMenuRoleAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return sysMenuRoleAO.querySYSMenuList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631056Req.class);
    }
}
