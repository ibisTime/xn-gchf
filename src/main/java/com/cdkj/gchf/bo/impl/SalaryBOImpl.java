package com.cdkj.gchf.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IBankCardBO;
import com.cdkj.gchf.bo.ISalaryBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.dao.ISalaryDAO;
import com.cdkj.gchf.domain.BankCard;
import com.cdkj.gchf.domain.Salary;
import com.cdkj.gchf.enums.ESalaryStatus;
import com.cdkj.gchf.exception.BizException;

@Component
public class SalaryBOImpl extends PaginableBOImpl<Salary> implements ISalaryBO {

    @Autowired
    private ISalaryDAO salaryDAO;

    @Autowired
    IBankCardBO bankCardBO;

    public void saveSalary(Salary data) {
        salaryDAO.insert(data);
    }

    @Override
    public void dropSalary(String code) {
        Salary salary = new Salary();
        salary.setCode(code);
        salaryDAO.delete(salary);
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
    public List<Salary> getEmploySalary(String employCode) {
        List<Salary> list = null;
        if (StringUtils.isNotBlank(employCode)) {
            Salary condition = new Salary();
            condition.setEmployCode(employCode);

            // 只统计已发工资的记录
            List<String> statusList = new ArrayList<String>();
            statusList.add(ESalaryStatus.Payed.getCode());
            statusList.add(ESalaryStatus.Pay_Portion.getCode());
            statusList.add(ESalaryStatus.Pay_Delay.getCode());
            statusList.add(ESalaryStatus.Pay_Delay_Portion.getCode());
            statusList.add(ESalaryStatus.Pay_Again.getCode());
            condition.setStatusList(statusList);

            list = salaryDAO.selectList(condition);

            if (CollectionUtils.isNotEmpty(list)) {
                for (Salary salary : list) {
                    // 工资卡
                    BankCard bankCard = bankCardBO
                        .getEmployBankCard(salary.getEmployCode());

                    if (null != bankCard) {
                        salary.setBankCard(bankCard);
                        salary.setBankcardNumber(bankCard.getBankcardNumber());
                    }

                }
            }
        }
        return list;
    }

    @Override
    public void refreshStatus(String code, String status) {
        Salary salary = new Salary();
        salary.setCode(code);
        salary.setStatus(status);
        salaryDAO.updateStatus(salary);
    }

    @Override
    public List<Salary> selectMonthlySalarySumByProject(String projectCode) {
        Salary condition = new Salary();
        condition.setProjectCode(projectCode);

        // 只统计已发工资的记录
        List<String> statusList = new ArrayList<String>();
        statusList.add(ESalaryStatus.Payed.getCode());
        statusList.add(ESalaryStatus.Pay_Portion.getCode());
        statusList.add(ESalaryStatus.Pay_Delay.getCode());
        statusList.add(ESalaryStatus.Pay_Delay_Portion.getCode());
        statusList.add(ESalaryStatus.Pay_Again.getCode());
        condition.setStatusList(statusList);
        return salaryDAO.selectMonthlySalarySumByProject(condition);
    }

}
