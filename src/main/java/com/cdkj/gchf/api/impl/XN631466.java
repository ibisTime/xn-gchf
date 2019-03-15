package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.IEmployAO;
import com.cdkj.gchf.ao.IProgressAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.dto.req.XN631466Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 列表查务雇佣人员
 * @author: nyc 
 * @since: 2018年4月29日 下午7:32:21 
 * @history:
 */
public class XN631466 extends AProcessor {

    private IEmployAO employAO = SpringContextHolder.getBean(IEmployAO.class);

    private XN631466Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Employ condition = new Employ();
        condition.setKeyword(req.getKeyword());
        condition.setProjectCode(req.getProjectCode());
        condition.setStatus(req.getStatus());
        condition.setType(req.getType());

        condition.setUpdater(req.getUpdater());
        condition.setProjectCodeList(req.getProjectCodeList());
        condition.setStaffCode(req.getStaffCode());
        condition.setStatusList(req.getStatusList());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IProgressAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return employAO.queryEmployList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631466Req.class);
    }

}
