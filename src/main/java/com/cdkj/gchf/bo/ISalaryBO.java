package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Salary;

public interface ISalaryBO extends IPaginableBO<Salary> {

    // 添加工资条
    public void saveSalary(Salary data);

    // 删除工资条
    public void dropSalary(String code);

    // 修改工资条
    public void refreshSalary(Salary data);

    // 审核工资条
    public void approveSalary(Salary data);

    // 发工资
    public void payAmount(Salary salary);

    // 更新状态
    public void refreshStatus(Salary salary);

    // 查询项目每月薪资总额
    public List<Salary> selectMonthlySalarySumByProject(String projectCode);

    // 查询雇员工资条
    public List<Salary> getEmploySalary(String employCode);

    public List<Salary> querySalaryList(Salary condition);

    public Salary getSalary(String code);

}
