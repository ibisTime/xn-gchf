package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cdkj.gchf.ao.IEquipmentInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.EquipmentInfo;
import com.cdkj.gchf.dto.req.XN631825Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * @ClassName: XN631825
 * @Description:分页查询设备
 * @author: Old3
 * @date: 2019年5月2日 下午3:52:34
 * @Copyright:
 */
public class XN631825 extends AProcessor {
    private IEquipmentInfoAO equipmentInfoAO = SpringContextHolder
            .getBean(IEquipmentInfoAO.class);

    private XN631825Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        EquipmentInfo equipmentInfo = new EquipmentInfo();
        BeanUtils.copyProperties(req, equipmentInfo);

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        String order = req.getOrderColumn();
        if (StringUtils.isBlank(req.getOrderColumn())) {
            order = IEquipmentInfoAO.DEFAULT_ORDER_COLUMN;
        }
        equipmentInfo.setOrder(order, req.getOrderDir());

        return equipmentInfoAO.queryEquipmentInfoPage(start, limit,
                equipmentInfo);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631825Req.class);
        ObjValidater.validateReq(req);
    }

}
