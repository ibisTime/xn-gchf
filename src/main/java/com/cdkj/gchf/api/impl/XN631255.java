package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cdkj.gchf.ao.ICorpBasicinfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.dto.req.XN631255Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 分页查询企业基本信息
 * @author: silver 
 * @since: Mar 12, 2019 5:10:46 PM 
 * @history:
 */
public class XN631255 extends AProcessor {
    private ICorpBasicinfoAO corpBasicinfoAO = SpringContextHolder
        .getBean(ICorpBasicinfoAO.class);

    private XN631255Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        CorpBasicinfo condition = new CorpBasicinfo();
        BeanUtils.copyProperties(req, condition);

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ICorpBasicinfoAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return corpBasicinfoAO.queryCorpBasicinfoPage(req.getUserId(), start,
            limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631255Req.class);
    }
}
