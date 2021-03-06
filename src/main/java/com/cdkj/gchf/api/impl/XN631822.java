package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IEquipmentInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631822Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * @ClassName: XN631822
 * @Description:禁用设备
 * @author: Old3
 * @date: 2019年5月8日 下午10:30:48
 * @Copyright:
 */
public class XN631822 extends AProcessor {

    private XN631822Req req = null;

    private IEquipmentInfoAO equipmentInfoAO = SpringContextHolder
            .getBean(IEquipmentInfoAO.class);

    @Override
    public Object doBusiness() throws BizException {
        equipmentInfoAO.disableEquipment(req.getDeviceKey(), req.getUserId());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631822Req.class);
        ObjValidater.validateReq(req);
    }

}
