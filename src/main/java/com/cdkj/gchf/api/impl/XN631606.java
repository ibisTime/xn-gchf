package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProjectWorkerAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631606Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631606   
 * @Description:详细查询项目人员
 * @author: Old3
 * @date:   2019年3月20日 下午5:26:04     
 * @Copyright:
 */
public class XN631606 extends AProcessor {

    private IProjectWorkerAO projectWorkerAO = SpringContextHolder
        .getBean(IProjectWorkerAO.class);

    private XN631606Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return projectWorkerAO.getProjectWorker(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631606Req.class);
        ObjValidater.validateReq(req);
    }

}
