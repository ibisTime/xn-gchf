package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ISYSConfigAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.dto.req.XN631012Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 修改系统参数
 * @author: xieyj 
 * @since: 2016年11月23日 下午5:54:40 
 * @history:
 */
public class XN631012 extends AProcessor {
    private ISYSConfigAO sysConfigAO = SpringContextHolder
        .getBean(ISYSConfigAO.class);

    private XN631012Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        sysConfigAO.editSYSConfig(StringValidater.toLong(req.getId()),
            req.getCvalue(), req.getUpdater(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631012Req.class);
        ObjValidater.validateReq(req);
    }

}
