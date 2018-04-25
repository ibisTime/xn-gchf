package com.cdkj.gchf.api.impl;

import org.apache.commons.collections.CollectionUtils;

import com.cdkj.gchf.ao.IWithdrawAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.dto.req.XN802752Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 批量审批线下取现订单
 * @author: myb858 
 * @since: 2017年4月24日 下午7:51:08 
 * @history:
 */
public class XN802752 extends AProcessor {
    private IWithdrawAO withdrawAO = SpringContextHolder
        .getBean(IWithdrawAO.class);

    private XN802752Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        for (String code : req.getCodeList()) {
            withdrawAO.approveOrder(code, req.getApproveUser(),
                req.getApproveResult(), req.getApproveNote(),
                req.getSystemCode());
        }
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802752Req.class);
        if (CollectionUtils.isEmpty(req.getCodeList())) {
            throw new BizException("订单列表不能为空");
        }
        StringValidater.validateBlank(req.getApproveUser(),
            req.getApproveResult(), req.getApproveNote(), req.getSystemCode());
    }
}
