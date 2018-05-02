package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.ISalaryAO;
import com.cdkj.gchf.bo.ISalaryBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.Salary;
import com.cdkj.gchf.dto.req.XN631442Req;
import com.cdkj.gchf.enums.EBoolean;
import com.cdkj.gchf.enums.ESalaryStatus;
import com.cdkj.gchf.exception.BizException;

@Service
public class SalaryAOImpl implements ISalaryAO {

    @Autowired
    private ISalaryBO salaryBO;

    @Override
    public String addSalary(Salary data) {
        salaryBO.saveSalary(data);
        return null;
    }

    @Override
    public void editSalary(XN631442Req req) {
        Salary data = salaryBO.getSalary(req.getCode());
        data.setEarlyDays(StringValidater.toInteger(req.getEarlyDays()));
        data.setDelayDays(StringValidater.toInteger(req.getDelayDays()));
        data.setLeavingDays(StringValidater.toDouble(req.getLeavingDays()));

        data.setTax(StringValidater.toLong(req.getTax()));
        data.setCutAmount(StringValidater.toLong(req.getCutAmount()));
        data.setCutNote(req.getCutNote());
        Long fact = data.getShouldAmount()
                - StringValidater.toLong(req.getTax())
                - StringValidater.toLong(req.getCutAmount());
        data.setFactAmount(fact);
        salaryBO.refreshSalary(data);
    }

    @Override
    public void approveSalary(String code, String approver, String approveNote,
            String result) {
        Salary data = salaryBO.getSalary(code);
        if (ESalaryStatus.To_Approve.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "工资条不处于待审核状态");
        }
        String status = ESalaryStatus.TO_Send.getCode();
        if (EBoolean.NO.getCode().equals(result)) {
            status = ESalaryStatus.To_Approve.getCode();
        }

        salaryBO.approveSalary(data, approver, approveNote, status);
    }

    @Override
    public void dropSalary(String code) {
    }

    @Override
    public Paginable<Salary> querySalaryPage(int start, int limit,
            Salary condition) {
        return salaryBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Salary> querySalaryList(Salary condition) {
        return salaryBO.querySalaryList(condition);
    }

    @Override
    public Salary getSalary(String code) {
        return salaryBO.getSalary(code);
    }

}
