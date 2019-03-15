package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.IBankCardAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.domain.BankCard;
import com.cdkj.gchf.dto.req.XN631426Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 列表查工资卡
 * @author: nyc 
 * @since: 2018年5月3日 下午7:37:02 
 * @history:
 */
public class XN631426 extends AProcessor {

    private IBankCardAO bankCardAO = SpringContextHolder
        .getBean(IBankCardAO.class);

    private XN631426Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        BankCard condition = new BankCard();
        condition.setProjectCode(req.getProjectCode());
        condition.setUpdater(req.getUpdater());
        condition.setStaffCode(req.getStaffCode());
        condition.setStatus(req.getStatus());
        condition.setUpdater(req.getUpdater());
        condition.setKeyword(req.getKeyword());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IBankCardAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return bankCardAO.queryBankCardList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631426Req.class);
    }

}
