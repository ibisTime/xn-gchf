package com.cdkj.gchf.api.impl;

import org.springframework.beans.BeanUtils;

import com.cdkj.gchf.ao.IEquipmentWorkerAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.domain.EquipmentWorker;
import com.cdkj.gchf.dto.req.XN631837Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * @ClassName: XN631837
 * @Description:TODO
 * @author: Old3
 * @date: 2019年5月7日 下午2:51:49
 * @Copyright:
 */
public class XN631837 extends AProcessor {
    private IEquipmentWorkerAO equipmentWorkerAO = SpringContextHolder
            .getBean(IEquipmentWorkerAO.class);

    private XN631837Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        EquipmentWorker equipmentWorker = new EquipmentWorker();
        BeanUtils.copyProperties(req, equipmentWorker);
        return equipmentWorkerAO.queryEquipmentWorkerList(equipmentWorker);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631837Req.class);
        ObjValidater.validateReq(req);
    }

}
