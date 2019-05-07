package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cdkj.gchf.ao.IEquipmentInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.domain.EquipmentInfo;
import com.cdkj.gchf.dto.req.XN631827Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631827   
 * @Description: 列表查询设备
 * @author: Old3
 * @date:   2019年5月2日 下午3:58:47     
 * @Copyright:
 */
public class XN631827 extends AProcessor {
    private IEquipmentInfoAO equipmentInfoAO = SpringContextHolder
        .getBean(IEquipmentInfoAO.class);

    private XN631827Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        EquipmentInfo equipmentInfo = new EquipmentInfo();
        BeanUtils.copyProperties(req, equipmentInfo);
        String order = req.getOrderColumn();
        equipmentInfo.setUserId(req.getUserId());
        if (StringUtils.isBlank(order)) {
            order = IEquipmentInfoAO.DEFAULT_ORDER_COLUMN;
        }
        equipmentInfo.setOrder(order, req.getOrderDir());
        return equipmentInfoAO.queryEquipmentInfoList(equipmentInfo);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631827Req.class);
        ObjValidater.validateReq(req);
    }

}
