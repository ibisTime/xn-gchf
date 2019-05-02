package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IEquipmentWorkerAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631830Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631830   
 * @Description:添加考勤人员
 * @author: Old3
 * @date:   2019年5月2日 下午4:34:19     
 * @Copyright:
 */
public class XN631830 extends AProcessor {
    private IEquipmentWorkerAO equipmentWorkerAO = SpringContextHolder
        .getBean(IEquipmentWorkerAO.class);

    private XN631830Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        equipmentWorkerAO.addEquipmentWorker(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631830Req.class);
        ObjValidater.validateReq(req);
    }

}
