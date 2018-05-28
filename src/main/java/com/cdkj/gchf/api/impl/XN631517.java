package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IAbnormalRemindAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631517Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 列表查询事件通知人
 * @author: nyc 
 * @since: 2018年5月28日 下午7:48:10 
 * @history:
 */
public class XN631517 extends AProcessor {

    private IAbnormalRemindAO abnormalRemindAO = SpringContextHolder
        .getBean(IAbnormalRemindAO.class);

    private XN631517Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return abnormalRemindAO.getAbnormalRemind(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631517Req.class);
        ObjValidater.validateReq(req);
    }

}
