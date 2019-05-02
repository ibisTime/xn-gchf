package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IEquipmentInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631820Req;
import com.cdkj.gchf.dto.res.PKCodeRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * @ClassName:  XN631820   
 * @Description: 考勤设备添加
 * @author: Old3
 * @date:   2019年5月2日 上午11:19:27     
 */
public class XN631820 extends AProcessor {
    private IEquipmentInfoAO equipmentInfoAO = SpringContextHolder
        .getBean(IEquipmentInfoAO.class);

    private XN631820Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(equipmentInfoAO.addEquipmentInfo(req));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631820Req.class);
        ObjValidater.validateReq(req);
    }

}
