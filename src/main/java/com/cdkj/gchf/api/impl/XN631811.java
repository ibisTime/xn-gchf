package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IPayRollDetailAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631811Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631811   
 * @Description:删除工资单详情
 * @author: Old3
 * @date:   2019年4月4日 下午12:50:52     
 * @Copyright:
 */
public class XN631811 extends AProcessor {
    private IPayRollDetailAO payRollDetailAO = SpringContextHolder
        .getBean(IPayRollDetailAO.class);

    private XN631811Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        payRollDetailAO.dropPayRollDetail(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631811Req.class);
        ObjValidater.validateReq(req);
    }

}
