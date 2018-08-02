package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.ICcontractAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.Ccontract;
import com.cdkj.gchf.dto.req.XN631406Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 列表查合同
 * @author: nyc 
 * @since: 2018年4月29日 下午10:21:06 
 * @history:
 */
public class XN631406 extends AProcessor {

    private ICcontractAO ccontractAO = SpringContextHolder
        .getBean(ICcontractAO.class);

    private XN631406Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Ccontract condition = new Ccontract();
        condition.setKeyword(req.getKeyword());
        condition.setUpdater(req.getUpdater());
        condition.setProjectCode(req.getProjectCode());

        condition.setProjectCodeList(req.getProjectCodeList());
        condition.setKind(req.getKind());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ICcontractAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return ccontractAO.queryCcontractList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631406Req.class);
        StringValidater.validateBlank(req.getProjectCode());
    }

}
