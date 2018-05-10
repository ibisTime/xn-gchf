package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.IProgressAO;
import com.cdkj.gchf.ao.ISalaryAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.Salary;
import com.cdkj.gchf.dto.req.XN631448Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 分页查询累积薪资
 * @author: nyc 
 * @since: 2018年5月1日 上午11:48:15 
 * @history:
 */
public class XN631448 extends AProcessor {

    private ISalaryAO salaryAO = SpringContextHolder.getBean(ISalaryAO.class);

    private XN631448Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Salary condition = new Salary();
        condition.setCompanyCode(req.getCompanyCode());
        condition.setCompanyName(req.getCompanyName());
        condition.setProjectCode(req.getProjectCode());
        condition.setProjectName(req.getProjectName());

        condition.setApproveUser(req.getApprover());
        condition.setKeyword(req.getKeyword());
        condition.setMessageCode(req.getMessageCode());
        condition.setMonth(StringValidater.toInteger(req.getMonth()));
        condition.setProjectCode(req.getProjectCode());

        condition.setStatus(req.getStatus());
        condition.setStatusList(req.getStatusList());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IProgressAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return salaryAO.queryTotalSalaryPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631448Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
