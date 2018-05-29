package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IEventRemindAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631511Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 删除事件通知人
 * @author: nyc 
 * @since: 2018年5月28日 下午7:48:10 
 * @history:
 */
public class XN631511 extends AProcessor {

    private IEventRemindAO eventRemindAO = SpringContextHolder
        .getBean(IEventRemindAO.class);

    private XN631511Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        eventRemindAO.dropEventRemind(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631511Req.class);
        ObjValidater.validateReq(req);
    }

}
