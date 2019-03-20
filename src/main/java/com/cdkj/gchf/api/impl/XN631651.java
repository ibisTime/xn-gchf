package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ITeamMasterAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631651Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631651   
 * @Description:删除班组
 * @author: Old3
 * @date:   2019年3月20日 下午1:15:14     
 * @Copyright:
 */
public class XN631651 extends AProcessor {

    private ITeamMasterAO teamMasterAO = SpringContextHolder
        .getBean(ITeamMasterAO.class);

    private XN631651Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        teamMasterAO.dropTeamMaster(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631651Req.class);
        ObjValidater.validateReq(req);
    }

}
