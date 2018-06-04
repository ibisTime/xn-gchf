package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.ISalaryBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.dao.ISalaryDAO;
import com.cdkj.gchf.domain.Salary;
import com.cdkj.gchf.enums.ESalaryStatus;
import com.cdkj.gchf.exception.BizException;

@Component
public class SalaryBOImpl extends PaginableBOImpl<Salary> implements ISalaryBO {

    @Autowired
    private ISalaryDAO salaryDAO;

    public void saveSalary(Salary data) {
        salaryDAO.insert(data);
    }

    @Override
    public void refreshSalary(Salary data) {
        salaryDAO.update(data);
    }

    @Override
    public List<Salary> querySalaryList(Salary condition) {
        return salaryDAO.selectList(condition);
    }

    @Override
    public Salary getSalary(String code) {
        Salary data = null;
        if (StringUtils.isNotBlank(code)) {
            Salary condition = new Salary();
            condition.setCode(code);
            data = salaryDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "工资条不存在");
            }
        }
        return data;
    }

    @Override
    public void approveSalary(Salary data) {
        salaryDAO.approveSalary(data);
    }

    @Override
    public void payAmount(Salary salary) {
        salaryDAO.payAmount(salary);

    }

    @Override
    public List<Salary> getSalaryByStaff(String staffCode, String projectCode) {
        List<Salary> list = null;
        if (StringUtils.isNotBlank(staffCode)
                && StringUtils.isNotBlank(projectCode)) {
            Salary condition = new Salary();
            condition.setStaffCode(staffCode);
            condition.setProjectCode(projectCode);
            list = salaryDAO.selectList(condition);
        }
        return list;
    }

    @Override
    public Salary querySalayByStatus(String projectCode, String staffCode,
            String month, String status) {
        Salary condition = new Salary();
        condition.setStaffCode(staffCode);
        condition.setProjectCode(projectCode);
        condition.setMonth(month);
        condition.setStatus(status);
        return salaryDAO.select(condition);
    }

    @Override
    public void refreshStatus(Salary salary) {
        salaryDAO.updateStatus(salary);
    }

    @Override
    public List<Salary> getAbnormalSalaryByStaff(String staffCode,
            String projectCode) {
        List<Salary> list = null;
        if (StringUtils.isNotBlank(staffCode)
                && StringUtils.isNotBlank(projectCode)) {
            Salary condition = new Salary();
            condition.setStaffCode(staffCode);
            condition.setProjectCode(projectCode);
            condition.setStatus(ESalaryStatus.Pay_Portion.getCode());
            list = salaryDAO.selectList(condition);
        }
        return list;
    }

}
