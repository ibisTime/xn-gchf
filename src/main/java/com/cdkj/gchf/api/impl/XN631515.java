package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.IAbnormalRemindAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.AbnormalRemind;
import com.cdkj.gchf.dto.req.XN631515Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 分页事件通知人
 * @author: nyc 
 * @since: 2018年5月28日 下午7:48:10 
 * @history:
 */
public class XN631515 extends AProcessor {

    private IAbnormalRemindAO abnormalRemindAO = SpringContextHolder
        .getBean(IAbnormalRemindAO.class);

    private XN631515Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        AbnormalRemind condition = new AbnormalRemind();
        condition.setKeyword(req.getKeyword());
        condition.setUpdater(req.getUpdater());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IAbnormalRemindAO.DEFAULT_ORDER_COLUMN;
        }
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return abnormalRemindAO.queryAbnormalRemindPage(start, limit,
            condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631515Req.class);
        ObjValidater.validateReq(req);
    }

}
