package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IEquipmentInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631824Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 解绑设备
 *
 * @author : old3
 * @since : 2019-05-30 16:09
 */
public class XN631824 extends AProcessor {

    private XN631824Req req = null;

    private IEquipmentInfoAO equipmentInfoAO = SpringContextHolder.getBean(IEquipmentInfoAO.class);

    @Override
    public Object doBusiness() throws BizException {
        equipmentInfoAO.delEquipment(req.getCode(), req.getDeviceKey());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631824Req.class);
        ObjValidater.validateReq(req);
    }
}

    
    