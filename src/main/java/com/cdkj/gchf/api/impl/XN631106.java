package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.ISubbranchAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.domain.Subbranch;
import com.cdkj.gchf.dto.req.XN631106Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 列表查询支行
 * @author: silver 
 * @since: 2018年6月25日 下午7:27:55 
 * @history:
 */
public class XN631106 extends AProcessor {

    private ISubbranchAO subbranchAO = SpringContextHolder
        .getBean(ISubbranchAO.class);

    private XN631106Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Subbranch condition = new Subbranch();

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ISubbranchAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        return subbranchAO.querySubbranchList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631106Req.class);
    }

}
