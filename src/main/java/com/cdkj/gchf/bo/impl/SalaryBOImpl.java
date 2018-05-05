package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.ISalaryBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.StringValidater;
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
    public void approveSalary(Salary data, String messageCode, String approver,
            String approveNote, String status) {
        data.setMessageCode(messageCode);
        data.setApproveUser(approver);
        data.setApproveDatetime(new Date());
        data.setApproveNote(approveNote);
        data.setStatus(status);
        salaryDAO.approveSalary(data);
    }

    @Override
    public void payAmount(Salary salary, String payAmount,
            String latePayDatetime) {
        salary.setStatus(ESalaryStatus.Payed.getCode());
        salary.setPayAmount(StringValidater.toLong(payAmount));
        salary.setLatePayDatetime(DateUtil.strToDate(latePayDatetime,
            DateUtil.FRONT_DATE_FORMAT_STRING));
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
}
