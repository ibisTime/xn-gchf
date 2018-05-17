package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.IBankCardAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.BankCard;
import com.cdkj.gchf.dto.req.XN631425Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 分页查工资卡
 * @author: nyc 
 * @since: 2018年5月3日 下午7:37:02 
 * @history:
 */
public class XN631425 extends AProcessor {

    private IBankCardAO bankCardAO = SpringContextHolder
        .getBean(IBankCardAO.class);

    private XN631425Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        BankCard condition = new BankCard();
        condition.setUpdater(req.getUpdater());
        condition.setStaffCode(req.getStaffCode());
        condition.setStatus(req.getStatus());
        condition.setUpdater(req.getUpdater());
        condition.setKeyword(req.getKeyword());

        condition.setKind(req.getKind());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IBankCardAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return bankCardAO.queryBankCardPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631425Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
