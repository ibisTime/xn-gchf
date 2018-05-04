package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.BankCard;

//dao层 
public interface IBankCardDAO extends IBaseDAO<BankCard> {
    String NAMESPACE = IBankCardDAO.class.getName().concat(".");

    void update(BankCard data);
}
