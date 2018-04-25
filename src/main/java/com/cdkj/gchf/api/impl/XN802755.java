package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.IWithdrawAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.Withdraw;
import com.cdkj.gchf.dto.req.XN802755Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 分页查询取现订单
 * @author: xieyj 
 * @since: 2017年5月17日 下午5:17:47 
 * @history:
 */
public class XN802755 extends AProcessor {
    private IWithdrawAO withdrawAO = SpringContextHolder
        .getBean(IWithdrawAO.class);

    private XN802755Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Withdraw condition = new Withdraw();
        if (StringUtils.isNotBlank(req.getAccountNumber())) {
            condition.setAccountNumber(req.getAccountNumber());
        }
        condition.setCodeForQuery(req.getCode());
        condition.setAccountName(req.getAccountName());
        condition.setType(req.getType());
        condition.setChannelType(req.getChannelType());
        condition.setStatus(req.getStatus());
        condition.setApplyUser(req.getApplyUser());

        condition.setApproveUser(req.getApproveUser());
        condition.setPayUser(req.getPayUser());
        condition.setPayGroup(req.getPayGroup());
        condition.setChannelOrder(req.getChannelOrder());
        condition.setCompanyCode(req.getCompanyCode());
        condition.setSystemCode(req.getSystemCode());

        condition.setApplyDatetimeStart(DateUtil.getFrontDate(
            req.getApplyDateStart(), false));
        condition.setApplyDatetimeEnd(DateUtil.getFrontDate(
            req.getApplyDateEnd(), true));
        condition.setApproveDatetimeStart(DateUtil.getFrontDate(
            req.getApproveDateStart(), false));
        condition.setApproveDatetimeEnd(DateUtil.getFrontDate(
            req.getApproveDateEnd(), true));
        condition.setPayDatetimeStart(DateUtil.getFrontDate(
            req.getPayDateStart(), false));
        condition.setPayDatetimeEnd(DateUtil.getFrontDate(req.getPayDateEnd(),
            true));

        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IWithdrawAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return withdrawAO.queryWithdrawPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802755Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
        StringValidater
            .validateBlank(req.getSystemCode(), req.getCompanyCode());

    }

}
