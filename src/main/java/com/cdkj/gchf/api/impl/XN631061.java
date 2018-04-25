package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ISYSMenuAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631061Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 菜单-删除
 * @author: xieyj 
 * @since: 2016年5月16日 下午11:00:43 
 * @history:
 */
public class XN631061 extends AProcessor {
    private ISYSMenuAO sysMenuAO = SpringContextHolder
        .getBean(ISYSMenuAO.class);

    private XN631061Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new BooleanRes(sysMenuAO.dropSYSMenu(req.getCode()));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631061Req.class);
        ObjValidater.validateReq(req);
    }
}
