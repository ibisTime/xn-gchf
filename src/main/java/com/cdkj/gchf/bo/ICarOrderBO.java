package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.CarOrder;

public interface ICarOrderBO extends IPaginableBO<CarOrder> {

    public String saveCarOrder(CarOrder data);

    public CarOrder getCarOrder(String code);

    public int editCarOrder(CarOrder data);

    public List<CarOrder> queryCarOrder(CarOrder condition);
}
