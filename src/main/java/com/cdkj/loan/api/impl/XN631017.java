package com.cdkj.loan.api.impl;

import com.cdkj.loan.ao.ISYSConfigAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.common.JsonUtil;
import com.cdkj.loan.core.ObjValidater;
import com.cdkj.loan.dto.req.XN631017Req;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

/**
 * 根据type获取value值
 * @author: xieyj 
 * @since: 2016年9月17日 下午1:56:04 
 * @history:
 */
public class XN631017 extends AProcessor {
    private ISYSConfigAO sysConfigAO = SpringContextHolder
        .getBean(ISYSConfigAO.class);

    private XN631017Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return sysConfigAO.getSYSConfigMap(req.getType());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631017Req.class);
        ObjValidater.validateReq(req);
    }

}
