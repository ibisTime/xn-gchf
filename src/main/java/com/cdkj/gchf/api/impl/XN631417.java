package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IStaffAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631417Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 详情查务工人员信息
 * @author: nyc 
 * @since: 2018年4月29日 下午8:37:32 
 * @history:
 */
public class XN631417 extends AProcessor {

    private IStaffAO staffAO = SpringContextHolder.getBean(IStaffAO.class);

    private XN631417Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        return staffAO.getStaff(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631417Req.class);
        ObjValidater.validateReq(req);
    }

}
