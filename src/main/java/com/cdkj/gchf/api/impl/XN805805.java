package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.ICNavigateAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.CNavigate;
import com.cdkj.gchf.dto.req.XN805805Req;
import com.cdkj.gchf.enums.EBoolean;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/** 
 * 分页查询导航
 * @author: zuixian 
 * @since: 2016年10月10日 下午3:58:13 
 * @history:
 */
public class XN805805 extends AProcessor {
    private ICNavigateAO cNavigateAO = SpringContextHolder
        .getBean(ICNavigateAO.class);

    private XN805805Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        CNavigate condition = new CNavigate();
        condition.setName(req.getName());
        condition.setType(req.getType());
        condition.setStatus(req.getStatus());
        condition.setLocation(req.getLocation());
        condition.setBelong(req.getBelong());
        condition.setParentCode(req.getParentCode());
        condition.setContentType(req.getContentType());
        condition.setCompanyCode(req.getCompanyCode());
        condition.setSystemCode(req.getSystemCode());
        condition.setIsFront(EBoolean.NO.getCode());
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ICNavigateAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return cNavigateAO.queryCNavigatePage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805805Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
        StringValidater
            .validateBlank(req.getCompanyCode(), req.getSystemCode());
    }
}
