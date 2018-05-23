package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IStaffAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631418Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 返回特征列表
 * @author: nyc 
 * @since: 2018年4月29日 下午8:37:32 
 * @history:
 */
public class XN631418 extends AProcessor {

    private IStaffAO staffAO = SpringContextHolder.getBean(IStaffAO.class);

    private XN631418Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return staffAO.getStaffByIdNo(req.getIdNo(), req.getProjectCodeList());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631418Req.class);
        ObjValidater.validateReq(req);
    }

}
