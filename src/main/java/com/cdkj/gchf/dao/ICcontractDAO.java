package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Ccontract;

//dao层 
public interface ICcontractDAO extends IBaseDAO<Ccontract> {
    String NAMESPACE = ICcontractDAO.class.getName().concat(".");

    void update(Ccontract data);
}
