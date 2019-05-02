package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IEquipmentInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631821Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631821   
 * @Description:修改设备信息
 * @author: Old3
 * @date:   2019年5月2日 下午3:14:17     
 * @Copyright:
 */
public class XN631821 extends AProcessor {
    private IEquipmentInfoAO equipmentInfoAO = SpringContextHolder
        .getBean(IEquipmentInfoAO.class);

    private XN631821Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        equipmentInfoAO.modifyEquipment(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631821Req.class);
        ObjValidater.validateReq(req);
    }

}
