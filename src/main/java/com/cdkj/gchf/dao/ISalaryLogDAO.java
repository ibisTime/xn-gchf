package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.SalaryLog;

//dao层 
public interface ISalaryLogDAO extends IBaseDAO<SalaryLog> {
    String NAMESPACE = ISalaryLogDAO.class.getName().concat(".");

    void update(SalaryLog data);

    void dealWithSalary(SalaryLog data);
}
