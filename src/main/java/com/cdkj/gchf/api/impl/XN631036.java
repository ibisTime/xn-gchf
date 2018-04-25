package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.IDepartmentAO;
import com.cdkj.gchf.ao.ISYSConfigAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.domain.Department;
import com.cdkj.gchf.dto.req.XN631036Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 列表查询部门
 * @author: nyc 
 * @since: 2018年4月25日 下午9:00:02 
 * @history:
 */
public class XN631036 extends AProcessor {

    private IDepartmentAO departmentAO = SpringContextHolder
        .getBean(IDepartmentAO.class);

    private XN631036Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Department condition = new Department();
        condition.setCompanyCode(req.getCompanyCode());
        condition.setParentCode(req.getParentCode());
        condition.setKeyword(req.getKeyword());

        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ISYSConfigAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());

        return departmentAO.queryDepartmentList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631036Req.class);
        ObjValidater.validateReq(req);
    }

}
