package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Salary;

public interface ISalaryBO extends IPaginableBO<Salary> {

    public void saveSalary(Salary data);

    public void removeSalary(String code);

    public void refreshSalary(Salary data);

    public List<Salary> querySalaryList(Salary condition);

    public Salary getSalary(String code);

    public void approveSalary(Salary data, String messageCode, String approver,
            String approveNote, String status);

    public void payAmount(Salary salary, Long payAmount,
            String latePayDatetime);

    public long getTotalSalaryCount(Salary condition);

    public List<Salary> queryTotalSalaryList(Salary condition);

    public List<Salary> queryTotalSalaryPage(int pageNO, int pageSize,
            Salary condition);

}
