package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IEquipmentInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631826Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631826   
 * @Description:详细 查询设备信息
 * @author: Old3
 * @date:   2019年5月2日 下午4:03:15     
 */
public class XN631826 extends AProcessor {
    private IEquipmentInfoAO equipmentInfoAO = SpringContextHolder
        .getBean(IEquipmentInfoAO.class);

    private XN631826Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return equipmentInfoAO.getEquipmentInfo(req.getUserId(), req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631826Req.class);
        ObjValidater.validateReq(req);

    }

}
