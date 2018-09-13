package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IStaffAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631520Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 根据身份证获取务工人员
 * @author: silver 
 * @since: 2018年9月13日 下午4:07:47 
 * @history:
 */
public class XN631520 extends AProcessor {

    private IStaffAO staffAO = SpringContextHolder.getBean(IStaffAO.class);

    private XN631520Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return staffAO.getStaffByIdNO(req.getIdNo(), req.getProjectCodeList());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631520Req.class);
        ObjValidater.validateReq(req);
    }

}
