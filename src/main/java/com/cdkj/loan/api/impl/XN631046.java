package com.cdkj.loan.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.loan.ao.ISYSRoleAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.common.JsonUtil;
import com.cdkj.loan.domain.SYSRole;
import com.cdkj.loan.dto.req.XN631046Req;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

/**
 * 角色-列表查询
 * @author: xieyj 
 * @since: 2016年4月17日 上午8:24:46 
 * @history:
 */
public class XN631046 extends AProcessor {
    private ISYSRoleAO sysRoleAO = SpringContextHolder
        .getBean(ISYSRoleAO.class);

    private XN631046Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSRole condition = new SYSRole();
        condition.setName(req.getName());
        condition.setLevel(req.getLevel());
        condition.setUpdater(req.getUpdater());
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ISYSRoleAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        return sysRoleAO.querySYSRoleList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631046Req.class);
    }
}
