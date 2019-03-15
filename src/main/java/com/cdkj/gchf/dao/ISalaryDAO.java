package com.cdkj.gchf.dao;

import java.util.List;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Salary;

//dao层 
public interface ISalaryDAO extends IBaseDAO<Salary> {
    String NAMESPACE = ISalaryDAO.class.getName().concat(".");

    void update(Salary data);

    void approveSalary(Salary data);

    void payAmount(Salary data);

    void updateStatus(Salary data);

    // 查询项目每月薪资总额
    public List<Salary> selectMonthlySalarySumByProject(Salary data);

}
