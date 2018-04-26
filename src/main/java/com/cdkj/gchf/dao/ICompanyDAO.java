package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Company;

public interface ICompanyDAO extends IBaseDAO<Company> {

    String NAMESPACE = ICompanyDAO.class.getName().concat(".");

    public int update(Company data);

}
