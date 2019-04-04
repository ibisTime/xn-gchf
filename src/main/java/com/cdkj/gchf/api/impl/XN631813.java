package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IPayRollAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631813Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631813   
 * @Description: 批量上传工资单
 * @author: Old3
 * @date:   2019年3月26日 下午2:10:12     
 * @Copyright:
 */
public class XN631813 extends AProcessor {
    private IPayRollAO payRollAO = SpringContextHolder
        .getBean(IPayRollAO.class);

    private XN631813Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        payRollAO.uploadPayRollList(req.getUserId(), req.getCodeList());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631813Req.class);
        ObjValidater.validateReq(req);
    }

}
