package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Salary;
import com.cdkj.gchf.domain.SalaryLog;

public interface ISalaryLogBO extends IPaginableBO<SalaryLog> {

    public String saveSalaryLog(SalaryLog data);

    public void removeSalaryLog(String code);

    public void refreshSalaryLog(SalaryLog data);

    public List<SalaryLog> querySalaryLogList(SalaryLog condition);

    public SalaryLog getSalaryLog(String code);

    public void saveSalaryLog(Salary salary, String companyCode,
            String companyName, String type, String handler, String handleNote);

}
