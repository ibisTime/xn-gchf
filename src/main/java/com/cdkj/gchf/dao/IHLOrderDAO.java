package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.HLOrder;

public interface IHLOrderDAO extends IBaseDAO<HLOrder> {
    String NAMESPACE = IHLOrderDAO.class.getName().concat(".");

    void approveOrder(HLOrder order);

}
