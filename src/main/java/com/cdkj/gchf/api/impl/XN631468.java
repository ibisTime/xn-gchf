package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.ILeaveAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.Leave;
import com.cdkj.gchf.dto.req.XN631468Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 分页查询请假明细
 * @author: silver 
 * @since: 2018年7月3日 下午4:30:26 
 * @history:
 */
public class XN631468 extends AProcessor {

    private ILeaveAO leaveAO = SpringContextHolder.getBean(ILeaveAO.class);

    private XN631468Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Leave condition = new Leave();
        condition.setEmployCode(req.getEmployCode());
        condition.setStaffCode(req.getStaffCode());
        condition.setProjectCode(req.getProjectCode());
        condition.setProjectCodeList(req.getProjectCodeList());

        condition.setStaffName(req.getStaffName());
        condition.setPosition(req.getPosition());
        condition.setDepartmentCode(req.getDepartmentCode());
        condition.setEmployStatus(req.getEmployStatus());
        condition.setKeyword(req.getKeyword());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ILeaveAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return leaveAO.queryLeavePage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631468Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
