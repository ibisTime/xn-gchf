package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.IMessageAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.domain.Message;
import com.cdkj.gchf.dto.req.XN631436Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 列表查代发消息
 * @author: nyc 
 * @since: 2018年5月1日 上午11:48:15 
 * @history:
 */
public class XN631436 extends AProcessor {

    private IMessageAO messageAO = SpringContextHolder
        .getBean(IMessageAO.class);

    private XN631436Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Message condition = new Message();
        condition.setCompanyCode(req.getCompanyCode());
        condition.setCompanyName(req.getCompanyName());
        condition.setProjectCode(req.getProjectCode());
        condition.setProjectName(req.getProjectName());

        condition.setSender(req.getSender());
        condition.setHandler(req.getHandler());
        condition.setStatus(req.getStatus());
        condition.setKeyword(req.getKeyword());
        condition.setStatusList(req.getStatusList());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IMessageAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return messageAO.queryMessageList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631436Req.class);
    }

}
