package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cdkj.gchf.ao.IPayRollDetailAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.PayRollDetail;
import com.cdkj.gchf.dto.req.XN631815Req;
import com.cdkj.gchf.enums.EDeleteStatus;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631815   
 * @Description:分页查工资单详情
 * @author: Old3
 * @date:   2019年4月1日 下午2:04:53     
 * @Copyright:
 */
public class XN631815 extends AProcessor {
    private IPayRollDetailAO payRollDetailAO = SpringContextHolder
        .getBean(IPayRollDetailAO.class);

    private XN631815Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        PayRollDetail payRollDetail = new PayRollDetail();
        BeanUtils.copyProperties(req, payRollDetail);
        payRollDetail.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        int limit = StringValidater.toInteger(req.getLimit());
        int start = StringValidater.toInteger(req.getStart());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IPayRollDetailAO.DEFAULT_ORDER_COLUMN;
        }
        if (StringUtils.isNotBlank(req.getBalanceDate())) {
            payRollDetail.setBalanceDate(DateUtil.strToDate(
                req.getBalanceDate(), DateUtil.FRONT_DATE_FORMAT_STRING));
        }
        payRollDetail.setOrder(orderColumn, req.getOrderDir());
        return payRollDetailAO.queryPayRollDetailPage(start, limit,
            payRollDetail);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631815Req.class);
        ObjValidater.validateReq(req);
    }

}
