package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IPayRollDetailAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.domain.PayRollDetail;
import com.cdkj.gchf.dto.req.XN631817Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631810   
 * @Description: 查看工资单详情
 * @author: Old3
 * @date:   2019年4月1日 上午11:27:24     
 * @Copyright:
 */
public class XN631817 extends AProcessor {

    private IPayRollDetailAO payRollDetailAO = SpringContextHolder
        .getBean(IPayRollDetailAO.class);

    private XN631817Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        PayRollDetail payDetailRoll = payRollDetailAO
            .getPayDetailRoll(req.getCode());
        return payDetailRoll;
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631817Req.class);
        ObjValidater.validateReq(req);
    }

}
