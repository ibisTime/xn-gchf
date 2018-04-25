package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.ICarAO;
import com.cdkj.gchf.bo.ICarBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.Car;
import com.cdkj.gchf.dto.req.XN630420Req;
import com.cdkj.gchf.dto.req.XN630422Req;
import com.cdkj.gchf.enums.EBrandStatus;
import com.cdkj.gchf.exception.BizException;

@Service
public class CarAOImpl implements ICarAO {

    @Autowired
    private ICarBO carBO;

    @Override
    public String addCar(XN630420Req req) {
        Car car = new Car();
        car.setName(req.getName());
        car.setSeriesCode(req.getSeriesCode());
        car.setSeriesName(req.getSeriesName());
        car.setBrandCode(req.getBrandCode());
        car.setBrandName(req.getBrandName());
        car.setOriginalPrice(StringValidater.toLong(req.getOriginalPrice()));
        car.setSalePrice(StringValidater.toLong(req.getSalePrice()));
        car.setSlogan(req.getSlogan());
        car.setAdvPic(req.getAdvPic());
        car.setPic(req.getPic());
        car.setDescription(req.getDescription());

        car.setStatus(EBrandStatus.TO_UP.getCode());
        car.setUpdater(req.getUpdater());
        car.setUpdateDatetime(new Date());
        car.setRemark(req.getRemark());
        return carBO.saveCar(car);
    }

    @Override
    public void editCar(XN630422Req req) {
        Car car = carBO.getCar(req.getCode());
        if (EBrandStatus.UP.getCode().equals(car.getStatus())) {
            throw new BizException("xn0000", "品牌已上架，请在下架后修改");
        }
        car.setName(req.getName());
        car.setSeriesCode(req.getSeriesCode());
        car.setSeriesName(req.getSeriesName());
        car.setBrandCode(req.getBrandCode());
        car.setBrandName(req.getBrandName());
        car.setOriginalPrice(StringValidater.toLong(req.getOriginalPrice()));
        car.setSalePrice(StringValidater.toLong(req.getSalePrice()));
        car.setSlogan(req.getSlogan());
        car.setAdvPic(req.getAdvPic());
        car.setPic(req.getPic());
        car.setDescription(req.getDescription());
        car.setUpdater(req.getUpdater());
        car.setUpdateDatetime(new Date());
        car.setRemark(req.getRemark());
        carBO.editCar(car);
    }

    @Override
    public void upCar(String code, String location, String orderNo,
            String updater, String remark) {
        Car car = carBO.getCar(code);
        car.setStatus(EBrandStatus.UP.getCode());
        car.setLocation(StringValidater.toInteger(location));
        car.setOrderNo(StringValidater.toInteger(orderNo));
        car.setUpdater(updater);
        car.setUpdateDatetime(new Date());
        car.setRemark(remark);
        carBO.editCar(car);
    }

    @Override
    public void downCar(String code, String updater, String remark) {
        Car car = carBO.getCar(code);
        car.setStatus(EBrandStatus.DOWN.getCode());
        car.setUpdater(updater);
        car.setUpdateDatetime(new Date());
        car.setRemark(remark);
        carBO.editCar(car);
    }

    @Override
    public Paginable<Car> queryCarPage(int start, int limit, Car condition) {
        return carBO.getPaginable(start, limit, condition);
    }

    @Override
    public Car getCar(String code) {
        return carBO.getCar(code);
    }

    @Override
    public List<Car> queryCarList(Car condition) {
        return carBO.queryCar(condition);
    }

}
