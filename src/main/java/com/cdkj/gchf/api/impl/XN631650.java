package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ITeamMasterAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631650Req;
import com.cdkj.gchf.dto.res.PKCodeRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631650   
 * @Description: 添加项目班组
 * @author: Old3
 * @date:   2019年3月20日 下午12:45:28     
 * @Copyright:
 */
public class XN631650 extends AProcessor {
    private ITeamMasterAO teamMasterAO = SpringContextHolder
        .getBean(ITeamMasterAO.class);

    private XN631650Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(teamMasterAO.addTeamMaster(req));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631650Req.class);
        ObjValidater.validateReq(req);
    }

}
