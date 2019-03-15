package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Bcontract;

//dao层 
public interface IBcontractDAO extends IBaseDAO<Bcontract> {
    String NAMESPACE = IBcontractDAO.class.getName().concat(".");

    public int updateBcontract(Bcontract data);
}
