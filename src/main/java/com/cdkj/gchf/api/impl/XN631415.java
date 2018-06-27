package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.IProgressAO;
import com.cdkj.gchf.ao.IStaffAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.dto.req.XN631415Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 分页查务工人员信息
 * @author: nyc 
 * @since: 2018年4月29日 下午8:37:32 
 * @history:
 */
public class XN631415 extends AProcessor {

    private IStaffAO staffAO = SpringContextHolder.getBean(IStaffAO.class);

    private XN631415Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Staff condition = new Staff();
        condition.setKeyword(req.getKeyword());
        condition.setIdType(req.getIdType());
        condition.setUpdater(req.getUpdater());
        condition.setIdNo(req.getIdNo());
        condition.setCompanyCode(req.getCompanyCode());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IProgressAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return staffAO.queryStaffPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631415Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
