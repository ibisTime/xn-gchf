package com.cdkj.loan.api.impl;

import com.cdkj.loan.ao.ISYSMenuAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.common.JsonUtil;
import com.cdkj.loan.core.ObjValidater;
import com.cdkj.loan.domain.SYSMenu;
import com.cdkj.loan.dto.req.XN631062Req;
import com.cdkj.loan.dto.res.BooleanRes;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

/**
 * 菜单-修改
 * @author: xieyj 
 * @since: 2016年5月16日 下午9:46:32 
 * @history:
 */
public class XN631062 extends AProcessor {
    private ISYSMenuAO sysMenuAO = SpringContextHolder
        .getBean(ISYSMenuAO.class);

    private XN631062Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSMenu condition = new SYSMenu();
        condition.setCode(req.getCode());
        condition.setName(req.getName());
        condition.setType(req.getType());
        condition.setUrl(req.getUrl());
        condition.setParentCode(req.getParentCode());
        condition.setOrderNo(req.getOrderNo());

        condition.setUpdater(req.getUpdater());
        condition.setRemark(req.getRemark());
        return new BooleanRes(sysMenuAO.editSYSMenu(condition));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631062Req.class);
        ObjValidater.validateReq(req);
    }
}
