package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Charge;

public interface IChargeDAO extends IBaseDAO<Charge> {
    String NAMESPACE = IChargeDAO.class.getName().concat(".");

    void payOrder(Charge data);
}
