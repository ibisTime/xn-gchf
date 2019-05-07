package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cdkj.gchf.ao.IEquipmentWorkerAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.EquipmentWorker;
import com.cdkj.gchf.dto.req.XN631835Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631835   
 * @Description:分页查询设备人员
 * @author: Old3
 * @date:   2019年5月7日 下午2:33:22     
 * @Copyright:
 */
public class XN631835 extends AProcessor {

    private IEquipmentWorkerAO equipmentWorkerAO = SpringContextHolder
        .getBean(IEquipmentWorkerAO.class);

    private XN631835Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        EquipmentWorker equipmentWorker = new EquipmentWorker();
        BeanUtils.copyProperties(req, equipmentWorker);
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        String order = req.getOrderColumn();

        if (StringUtils.isBlank(order)) {
            order = IEquipmentWorkerAO.DEFAULT_ORDER_COLUMN;
        }
        equipmentWorker.setOrder(order, req.getOrderDir());

        return equipmentWorkerAO.queryEquipmentWorkerPage(start, limit,
            equipmentWorker);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631835Req.class);
        ObjValidater.validateReq(req);
    }

}
