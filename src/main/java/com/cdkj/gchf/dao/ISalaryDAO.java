package com.cdkj.gchf.dao;

import java.util.List;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Salary;

//dao层 
public interface ISalaryDAO extends IBaseDAO<Salary> {
    String NAMESPACE = ISalaryDAO.class.getName().concat(".");

    void update(Salary data);

    void addMessageCode(Salary data);

    void payAmount(Salary data);

    long getTotalSalaryCount(Salary condition);

    List<Salary> queryTotalSalaryList(Salary condition);

    List<Salary> queryTotalSalaryPage(int pageNO, int pageSize,
            Salary condition);

    void updateStatus(Salary data);

}
