package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.ISalaryBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.dao.ISalaryDAO;
import com.cdkj.gchf.domain.Salary;
import com.cdkj.gchf.exception.BizException;

@Component
public class SalaryBOImpl extends PaginableBOImpl<Salary> implements ISalaryBO {

    @Autowired
    private ISalaryDAO salaryDAO;

    public void saveSalary(Salary data) {
        salaryDAO.insert(data);
    }

    @Override
    public void removeSalary(String code) {
        Salary data = new Salary();
        data.setCode(code);
        salaryDAO.delete(data);
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
    public void approveSalary(Salary data, String approver, String approveNote,
            String status) {
        data.setApproveUser(approver);
        data.setApproveDatetime(new Date());
        data.setApproveNot(approveNote);
        data.setStatus(status);
        salaryDAO.approveSalary(data);
    }
}
