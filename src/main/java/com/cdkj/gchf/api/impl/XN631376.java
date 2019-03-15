package com.cdkj.gchf.api.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.IBcontractAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.domain.Bcontract;
import com.cdkj.gchf.dto.req.XN631376Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 承包商合同列表查询
 * @author: CYL 
 * @since: 2018年4月28日 下午4:49:15 
 * @history:
 */
public class XN631376 extends AProcessor {
    private IBcontractAO bcontractAO = SpringContextHolder
        .getBean(IBcontractAO.class);

    private XN631376Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Bcontract condition = new Bcontract();
        condition.setProjectCode(req.getProjectCode());
        condition.setUpdater(req.getUpdater());
        condition.setUpdateDatetime(new Date());
        condition.setKeyword(req.getKeyword());

        condition.setProjectCodeList(req.getProjectCodeList());
        condition.setKind(req.getKind());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IBcontractAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return bcontractAO.queryBcontractList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631376Req.class);
    }

}
