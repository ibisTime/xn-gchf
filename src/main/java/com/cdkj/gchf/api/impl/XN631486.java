package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.IProgressAO;
import com.cdkj.gchf.ao.IStaffLogAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.StaffLog;
import com.cdkj.gchf.dto.req.XN631485Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 分页查务工人员记录
 * @author: nyc 
 * @since: 2018年4月29日 下午8:37:32 
 * @history:
 */
public class XN631486 extends AProcessor {

    private IStaffLogAO staffLogAO = SpringContextHolder
        .getBean(IStaffLogAO.class);

    private XN631485Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        StaffLog condition = new StaffLog();
        condition.setStaffCode(req.getStaffCode());
        condition.setProjectCodeList(req.getProjectCodeList());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IProgressAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return staffLogAO.queryStaffLogList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631485Req.class);
        StringValidater.validateBlank(req.getStaffCode());
    }

}
