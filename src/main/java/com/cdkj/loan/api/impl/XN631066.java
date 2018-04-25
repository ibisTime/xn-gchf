package com.cdkj.loan.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.loan.ao.ISYSMenuAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.common.JsonUtil;
import com.cdkj.loan.domain.SYSMenu;
import com.cdkj.loan.dto.req.XN631066Req;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

/**
 * 菜单-列表查询
 * @author: xieyj 
 * @since: 2016年5月16日 下午9:38:06 
 * @history:
 */
public class XN631066 extends AProcessor {
    private ISYSMenuAO sysMenuAO = SpringContextHolder
        .getBean(ISYSMenuAO.class);

    private XN631066Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSMenu condition = new SYSMenu();
        condition.setNameForQuery(req.getName());
        condition.setType(req.getType());
        condition.setParentCode(req.getParentCode());
        condition.setUpdater(req.getUpdater());
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ISYSMenuAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        return sysMenuAO.querySYSMenuList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631066Req.class);
    }
}
