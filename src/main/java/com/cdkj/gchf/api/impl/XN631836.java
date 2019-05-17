package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IEquipmentWorkerAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631836Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * @ClassName: XN631836
 * @Description:详细 设备人员
 * @author: Old3
 * @date: 2019年5月7日 下午2:45:09
 * @Copyright:
 */
public class XN631836 extends AProcessor {

    private IEquipmentWorkerAO equipmentWorkerAO = SpringContextHolder
            .getBean(IEquipmentWorkerAO.class);

    private XN631836Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return equipmentWorkerAO.getEquipmentWorker(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631836Req.class);
        ObjValidater.validateReq(req);
    }

}
