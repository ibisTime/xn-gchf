package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Salary;

//dao层 
public interface ISalaryDAO extends IBaseDAO<Salary> {
    String NAMESPACE = ISalaryDAO.class.getName().concat(".");

    void update(Salary data);

    void approveSalary(Salary data);

    void payAmount(Salary data);
}
