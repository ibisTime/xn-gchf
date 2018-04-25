package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.IUserAO;
import com.cdkj.gchf.ao.IUserRelationAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.domain.UserRelation;
import com.cdkj.gchf.dto.req.XN805115Req;
import com.cdkj.gchf.enums.EUserReleationType;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 分页查询关注人
 *
 * @author: xieyj
 * @since: 2016年8月31日 下午12:44:27
 * @history:
 */
public class XN805115 extends AProcessor {
    private IUserRelationAO userRelationAO = SpringContextHolder
        .getBean(IUserRelationAO.class);

    private XN805115Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        UserRelation condition = new UserRelation();
        condition.setUserId(req.getUserId());
        condition.setToUser(req.getToUser());

        String type = req.getType();
        if (StringUtils.isBlank(type)) {

            type = EUserReleationType.TRUST.getCode();

        }

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IUserAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        condition.setType(type);
        int start = Integer.valueOf(req.getStart());
        int limit = Integer.valueOf(req.getLimit());
        return userRelationAO.queryUserRelationPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805115Req.class);
        ObjValidater.validateReq(req);
    }
}
