package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.CompanyCard;

//dao层 
public interface ICompanyCardDAO extends IBaseDAO<CompanyCard> {
    String NAMESPACE = ICompanyCardDAO.class.getName().concat(".");

    void update(CompanyCard data);
}
