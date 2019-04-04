package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IPayRollDetailAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631810Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631811   
 * @Description: 修改工资单详情
 * @author: Old3
 * @date:   2019年4月1日 上午11:49:03     
 * @Copyright:
 */
public class XN631810 extends AProcessor {
    private IPayRollDetailAO payRollDetailAO = SpringContextHolder
        .getBean(IPayRollDetailAO.class);

    private XN631810Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        payRollDetailAO.editPayRollDetail(req);
        return new Boolean(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631810Req.class);
        ObjValidater.validateReq(req);
    }

}
