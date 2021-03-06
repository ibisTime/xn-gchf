package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IWorkerInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631791Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/***
 * @ClassName:  XN631791   
 * @Description:添加人员实名制-身份证信息
 * @author: Old3
 * @date:   2019年4月1日 下午2:33:51     
 * @Copyright:
 */
public class XN631791 extends AProcessor {
    private IWorkerInfoAO workerInfoAO = SpringContextHolder
        .getBean(IWorkerInfoAO.class);

    private XN631791Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        workerInfoAO.addWorkerInfoIdCardInfo(req);
        return new Boolean(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631791Req.class);
        ObjValidater.validateReq(req);
    }

}
