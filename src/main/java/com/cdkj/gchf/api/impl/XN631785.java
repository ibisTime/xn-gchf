package com.cdkj.gchf.api.impl;

import org.springframework.beans.BeanUtils;

import com.cdkj.gchf.ao.IPayRollAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.PayRoll;
import com.cdkj.gchf.dto.req.XN631785Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  分页查询工资单   
 * @Description:TODO
 * @author: Old3
 * @date:   2019年3月23日 下午2:32:55     
 * @Copyright:
 */
public class XN631785 extends AProcessor {
    private IPayRollAO payRollAO = SpringContextHolder
        .getBean(IPayRollAO.class);

    private XN631785Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        PayRoll condition = new PayRoll();
        BeanUtils.copyProperties(req, condition);
        return payRollAO.queryPayRollPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631785Req.class);
        ObjValidater.validateReq(req);
    }

}
