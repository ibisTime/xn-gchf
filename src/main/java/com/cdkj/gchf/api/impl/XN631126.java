package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.IOperatorGuideAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.domain.OperatorGuide;
import com.cdkj.gchf.dto.req.XN631126Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 列表查询操作指南
 * @author: silver 
 * @since: 2018年8月7日 上午9:48:21 
 * @history:
 */
public class XN631126 extends AProcessor {

    private IOperatorGuideAO operatorGuideAO = SpringContextHolder
        .getBean(IOperatorGuideAO.class);

    private XN631126Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        OperatorGuide condition = new OperatorGuide();
        condition.setSystemCode(req.getSystemCode());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IOperatorGuideAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return operatorGuideAO.queryOperatorGuideList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631126Req.class);
        ObjValidater.validateReq(req);
    }

}
