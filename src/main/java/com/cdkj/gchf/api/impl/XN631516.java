package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.IAbnormalRemindAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.domain.AbnormalRemind;
import com.cdkj.gchf.dto.req.XN631516Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 列表查询事件通知人
 * @author: nyc 
 * @since: 2018年5月28日 下午7:48:10 
 * @history:
 */
public class XN631516 extends AProcessor {

    private IAbnormalRemindAO abnormalRemindAO = SpringContextHolder
        .getBean(IAbnormalRemindAO.class);

    private XN631516Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        AbnormalRemind condition = new AbnormalRemind();
        condition.setKeyword(req.getKeyword());
        condition.setUpdater(req.getUpdater());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IAbnormalRemindAO.DEFAULT_ORDER_COLUMN;
        }

        return abnormalRemindAO.queryAbnormalRemindList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631516Req.class);
    }

}
