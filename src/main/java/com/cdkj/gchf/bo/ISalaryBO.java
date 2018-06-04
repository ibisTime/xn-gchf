package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Salary;

public interface ISalaryBO extends IPaginableBO<Salary> {

    public void saveSalary(Salary data);

    public void refreshSalary(Salary data);

    public List<Salary> querySalaryList(Salary condition);

    public Salary getSalary(String code);

    public void approveSalary(Salary data);

    public void payAmount(Salary salary);

    public List<Salary> getSalaryByStaff(String staffCode, String projectCode);

    public Salary querySalayByStatus(String projectCode, String staffCode,
            String month, String status);

    public void refreshStatus(Salary salary);

    public List<Salary> getAbnormalSalaryByStaff(String code,
            String projectCode);

}
