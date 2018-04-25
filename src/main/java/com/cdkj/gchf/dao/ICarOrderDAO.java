package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.CarOrder;

public interface ICarOrderDAO extends IBaseDAO<CarOrder> {
    String NAMESPACE = ICarOrderDAO.class.getName().concat(".");

    public int update(CarOrder data);
}
