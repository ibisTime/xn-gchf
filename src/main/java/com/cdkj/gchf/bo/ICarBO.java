package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Car;

public interface ICarBO extends IPaginableBO<Car> {

    public String saveCar(Car data);

    public Car getCar(String code);

    public int editCar(Car data);

    public List<Car> queryCar(Car condition);
}
