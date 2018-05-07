package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.IProgressAO;
import com.cdkj.gchf.ao.ISalaryLogAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.SalaryLog;
import com.cdkj.gchf.dto.req.XN631455Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 分页查询工资条日志
 * @author: nyc 
 * @since: 2018年5月1日 上午11:48:15 
 * @history:
 */
public class XN631455 extends AProcessor {

    private ISalaryLogAO salaryAO = SpringContextHolder
        .getBean(ISalaryLogAO.class);

    private XN631455Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SalaryLog condition = new SalaryLog();

        condition.setCompanyCode(req.getCompanyCode());
        condition.setCompanyName(req.getCompanyName());
        condition.setProjectCode(req.getProjectCode());
        condition.setProjectName(req.getProjectName());

        condition.setHandler(req.getHandler());
        condition.setSalaryCode(req.getSalaryCode());
        condition.setStaffCode(req.getStaffCode());
        condition.setType(req.getType());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IProgressAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return salaryAO.querySalaryLogPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631455Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
