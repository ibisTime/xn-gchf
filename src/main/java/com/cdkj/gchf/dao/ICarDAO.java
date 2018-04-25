package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Car;

public interface ICarDAO extends IBaseDAO<Car> {
    String NAMESPACE = ICarDAO.class.getName().concat(".");

    public int update(Car data);

    public int updateUp(Car data);

    public int updateDown(Car data);
}
