package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.ICarOrderAO;
import com.cdkj.gchf.bo.ICarOrderBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.CarOrder;
import com.cdkj.gchf.dto.req.XN630430Req;
import com.cdkj.gchf.enums.ECarOrderStatus;
import com.cdkj.gchf.exception.BizException;

@Service
public class CarOrderAOImpl implements ICarOrderAO {

    @Autowired
    private ICarOrderBO carOrderBO;

    @Override
    public String addCarOrder(XN630430Req req) {
        CarOrder carOrder = new CarOrder();
        carOrder.setUserId(req.getUserId());
        carOrder.setUserMobile(req.getUserMobile());
        carOrder.setBrandCode(req.getBrandCode());
        carOrder.setBrandName(req.getBrandName());
        carOrder.setSeriesCode(req.getSeriesCode());
        carOrder.setSeriesName(req.getSeriesName());
        carOrder.setCarCode(req.getCarCode());
        carOrder.setCarName(req.getCarName());
        carOrder.setPrice(StringValidater.toLong(req.getPrice()));
        carOrder.setSfRate(StringValidater.toDouble(req.getSfRate()));
        carOrder.setSfAmount(StringValidater.toLong(req.getSfAmount()));
        carOrder.setPeriods(StringValidater.toInteger(req.getPeriods()));
        carOrder.setCreateDatetime(new Date());
        carOrder.setSaleDesc(req.getSaleDesc());
        carOrder.setStatus(ECarOrderStatus.DCL.getCode());
        carOrder.setRemark(req.getRemark());
        return carOrderBO.saveCarOrder(carOrder);
    }

    @Override
    public void editCarOrder(String code, String result, String handler,
            String remark) {
        CarOrder carOrder = carOrderBO.getCarOrder(code);
        if (ECarOrderStatus.DCL.getCode().equals(carOrder.getStatus())) {
            if (result.equals(0)) {
                carOrder.setStatus(ECarOrderStatus.YCL.getCode());
            } else {
                carOrder.setStatus(ECarOrderStatus.YZF.getCode());
            }
            carOrder.setHandler(handler);
            carOrder.setHandleDatetime(new Date());
            carOrder.setRemark(remark);
            carOrderBO.editCarOrder(carOrder);
        } else {
            throw new BizException("mag", "该申请已被处理，请重新选择");
        }

    }

    @Override
    public Paginable<CarOrder> queryCarPage(int start, int limit,
            CarOrder condition) {
        return carOrderBO.getPaginable(start, limit, condition);
    }

    @Override
    public CarOrder getCarOrder(String code) {
        return carOrderBO.getCarOrder(code);
    }

    @Override
    public List<CarOrder> queryCarOrderList(CarOrder condition) {
        return carOrderBO.queryCarOrder(condition);
    }

}
