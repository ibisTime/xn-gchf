package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cdkj.gchf.ao.IPayRollDetailAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.domain.PayRollDetail;
import com.cdkj.gchf.dto.req.XN631816Req;
import com.cdkj.gchf.enums.EDeleteStatus;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631816   
 * @Description: 列表查工资单详情
 * @author: Old3
 * @date:   2019年4月1日 下午2:11:04     
 * @Copyright:
 */
public class XN631816 extends AProcessor {
    private IPayRollDetailAO payRollDetailAO = SpringContextHolder
        .getBean(IPayRollDetailAO.class);

    private XN631816Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        PayRollDetail payRollDetail = new PayRollDetail();
        BeanUtils.copyProperties(req, payRollDetail);
        payRollDetail.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IPayRollDetailAO.DEFAULT_ORDER_COLUMN;
        }
        payRollDetail.setOrder(orderColumn, req.getOrderDir());
        return payRollDetailAO.queryPayRollDetailList(payRollDetail);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631816Req.class);
        ObjValidater.validateReq(req);
    }

}
