package com.cdkj.loan.api.impl;

import com.cdkj.loan.ao.ISYSMenuAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.common.JsonUtil;
import com.cdkj.loan.core.ObjValidater;
import com.cdkj.loan.domain.SYSMenu;
import com.cdkj.loan.dto.req.XN631060Req;
import com.cdkj.loan.dto.res.PKCodeRes;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

/**
 * 菜单-增加
 * @author: xieyj 
 * @since: 2016年5月16日 下午10:59:56 
 * @history:
 */
public class XN631060 extends AProcessor {

    private ISYSMenuAO sysMenuAO = SpringContextHolder
        .getBean(ISYSMenuAO.class);

    private XN631060Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSMenu data = new SYSMenu();
        data.setName(req.getName());
        data.setType(req.getType());
        data.setUrl(req.getUrl());
        data.setParentCode(req.getParentCode());
        data.setOrderNo(req.getOrderNo());

        data.setUpdater(req.getUpdater());
        data.setRemark(req.getRemark());
        return new PKCodeRes(sysMenuAO.addSYSMenu(data));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631060Req.class);
        ObjValidater.validateReq(req);
    }
}
