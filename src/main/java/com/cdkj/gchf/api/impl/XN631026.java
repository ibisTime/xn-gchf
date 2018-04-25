package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ICompanyAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.domain.Company;
import com.cdkj.gchf.dto.req.XN631026Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 分页查询
 * @author: CYL 
 * @since: 2018年4月24日 下午5:32:51 
 * @history:
 */
public class XN631026 extends AProcessor {

    private ICompanyAO companyAO = SpringContextHolder
        .getBean(ICompanyAO.class);

    private XN631026Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Company condition = new Company();
        condition.setName(req.getName());

        return companyAO.queryCompanyList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631026Req.class);
    }

}
