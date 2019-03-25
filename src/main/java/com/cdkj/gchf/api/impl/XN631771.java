package com.cdkj.gchf.api.impl;

import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IPayRollAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631771Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631771   
 * @Description:删除工资单
 * @author: Old3
 * @date:   2019年3月23日 上午11:06:07     
 * @Copyright:
 */
public class XN631771 extends AProcessor {
    private IPayRollAO payRollAO = SpringContextHolder
        .getBean(IPayRollAO.class);

    private XN631771Req req = null;

    @Transactional
    @Override
    public Object doBusiness() throws BizException {
        payRollAO.dropPayRoll(req.getCode());
        return new Boolean(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631771Req.class);
        ObjValidater.validateReq(req);
    }

}
