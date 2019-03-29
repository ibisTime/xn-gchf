package com.cdkj.gchf.api.impl;

import org.springframework.beans.BeanUtils;

import com.cdkj.gchf.ao.IPayRollAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.domain.PayRoll;
import com.cdkj.gchf.dto.req.XN631787Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631787   
 * @Description:列表查工资单
 * @author: Old3
 * @date:   2019年3月23日 下午3:09:08     
 * @Copyright:
 */
public class XN631787 extends AProcessor {
    private IPayRollAO payRollAO = SpringContextHolder
        .getBean(IPayRollAO.class);

    private XN631787Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        PayRoll condition = new PayRoll();
        BeanUtils.copyProperties(req, condition);
        String column = null;
        if (req.getOrderColumn() == null) {
            column = IPayRollAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        return payRollAO.queryPayRollList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631787Req.class);
        ObjValidater.validateReq(req);
    }
}
