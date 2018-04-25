package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ISYSMenuAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN630017Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 菜单-详情
 * @author: xieyj 
 * @since: 2016年5月16日 下午9:39:48 
 * @history:
 */
public class XN630017 extends AProcessor {
    private ISYSMenuAO sysMenuAO = SpringContextHolder
        .getBean(ISYSMenuAO.class);

    private XN630017Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return sysMenuAO.getSYSMenu(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630017Req.class);
        ObjValidater.validateReq(req);
    }
}
