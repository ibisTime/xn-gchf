package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.ICompanyCardAO;
import com.cdkj.gchf.ao.IProjectAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.domain.CompanyCard;
import com.cdkj.gchf.dto.req.XN631366Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 列表查公司账户
 * @author: nyc 
 * @since: 2018年4月27日 下午9:46:22 
 * @history:
 */
public class XN631366 extends AProcessor {

    private ICompanyCardAO companyCardAO = SpringContextHolder
        .getBean(ICompanyCardAO.class);

    private XN631366Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        CompanyCard condition = new CompanyCard();

        condition.setCompanyCode(req.getCompanyCode());
        condition.setKeyword(req.getKeyword());
        condition.setProjectCode(req.getProjectCode());
        condition.setStatus(req.getStatus());
        condition.setKind(req.getKind());

        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IProjectAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        return companyCardAO.queryCompanyCardList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631366Req.class);
    }

}
