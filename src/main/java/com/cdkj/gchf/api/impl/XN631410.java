package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IStaffAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631410Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 录入务工人员
 * @author: nyc 
 * @since: 2018年4月29日 下午8:37:32 
 * @history:
 */
public class XN631410 extends AProcessor {

    private IStaffAO staffAO = SpringContextHolder.getBean(IStaffAO.class);

    private XN631410Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        return staffAO.addStaff(req);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631410Req.class);
        ObjValidater.validateReq(req);
    }

}
