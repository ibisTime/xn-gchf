package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.ISalaryBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.ISalaryDAO;
import com.cdkj.gchf.domain.Salary;
import com.cdkj.gchf.enums.EGeneratePrefix;
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
    public long getTotalSalaryCount(Salary condition) {
        return salaryDAO.getTotalSalaryCount(condition);
    }

    @Override
    public List<Salary> queryTotalSalaryList(Salary condition) {
        return salaryDAO.queryTotalSalaryList(condition);
    }

    @Override
    public List<Salary> queryTotalSalaryPage(int pageNO, int pageSize,
            Salary condition) {
        return salaryDAO.queryTotalSalaryPage(pageNO, pageSize, condition);
    }

    @Override
    public void saveNewSalay(Salary salary, String mCode, Long payAmount) {
        Salary data = new Salary();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Salary.getCode());
        data.setCode(code);
        data.setMessageCode(mCode);
        data.setStaffCode(salary.getStaffCode());

        data.setProjectCode(salary.getProjectCode());
        data.setProjectName(salary.getProjectName());
        data.setMonth(salary.getMonth());
        data.setShouldAmount(salary.getFactAmount() - payAmount);
        data.setFactAmount(salary.getFactAmount() - payAmount);

        data.setCutAmount(0L);
        data.setTax(0L);
        data.setDelayDays(0);
        data.setEarlyDays(0);

        data.setLeavingDays(0.0);
        data.setCreateDatetime(new Date());
        data.setApproveUser(salary.getApproveUser());
        data.setApproveDatetime(salary.getApproveDatetime());
        data.setApproveNote(salary.getApproveNote());

        data.setStatus(ESalaryStatus.Pay_Portion.getCode());
        data.setRemark(salary.getRemark());
        data.setLatePayDatetime(salary.getLatePayDatetime());
        salaryDAO.insert(data);
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

}
