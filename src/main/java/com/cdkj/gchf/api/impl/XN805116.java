package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.IUserRelationAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.dto.req.XN805116Req;
import com.cdkj.gchf.enums.EUserReleationType;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 关注人是否存在关联
 *
 * @author: xieyj
 * @since: 2016年8月31日 上午9:26:10
 * @history:
 */
public class XN805116 extends AProcessor {
    private IUserRelationAO userRelationAO = SpringContextHolder
            .getBean(IUserRelationAO.class);

    private XN805116Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        String type = req.getType();
        if (StringUtils.isBlank(type)) {
            type = EUserReleationType.TRUST.getCode();
        }
        return userRelationAO.isExistUserRelation(req.getUserId(),
                req.getToUser(), type);
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805116Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getToUser());
    }
}
