package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.ICUserAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.CUser;
import com.cdkj.gchf.dto.req.XN630206Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 列表查询
 * @author: CYL 
 * @since: 2018年4月24日 下午5:33:26 
 * @history:
 */
public class XN630206 extends AProcessor {

    private ICUserAO cuserAO = SpringContextHolder.getBean(ICUserAO.class);

    private XN630206Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        CUser condition = new CUser();
        condition.setLoginName(req.getLoginName());
        condition.setMobile(req.getMobile());
        condition.setUserReferee(req.getUserReferee());
        condition.setIdKind(req.getIdKind());
        condition.setIdNo(StringValidater.toLong(req.getIdNo()));
        condition.setRealName(req.getRealName());
        condition.setStatus(req.getStatus());

        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ICUserAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        return cuserAO.queryCUserList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630206Req.class);

    }

}
