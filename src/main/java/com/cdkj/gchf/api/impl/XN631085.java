package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.IUserAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631085Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 分页查用户
 * @author: nyc 
 * @since: 2018年4月24日 上午11:30:04 
 * @history:
 */
public class XN631085 extends AProcessor {

    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN631085Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        User condition = new User();
        condition.setType(req.getType());
        condition.setStatus(req.getStatus());
        condition.setUserRefree(req.getUserRefree());
        condition.setStatus(req.getStatus());

        condition.setRoleCode(req.getRoleCode());
        condition.setUpdater(req.getUpdater());
        condition.setCreateDatetimeStart(
            DateUtil.getFrontDate(req.getDateStart(), false));
        condition.setCreateDatetimeEnd(
            DateUtil.getFrontDate(req.getDateEnd(), true));

        condition.setProjectCode(req.getProjectCode());
        condition.setProvince(req.getProvince());
        condition.setCity(req.getCity());
        condition.setArea(req.getArea());
        condition.setBankName(req.getBankName());
        condition.setSubbranch(req.getSubbranch());

        condition.setKeyword(req.getKeyword());
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IUserAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = Integer.valueOf(req.getStart());
        int limit = Integer.valueOf(req.getLimit());
        return userAO.queryUserPage(start, limit, condition);

    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631085Req.class);
        StringValidater.validateBlank(req.getStart(), req.getLimit());
    }

}
