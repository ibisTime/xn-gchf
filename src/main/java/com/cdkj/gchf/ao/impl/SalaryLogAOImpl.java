package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.ISalaryLogAO;
import com.cdkj.gchf.bo.ISalaryLogBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.SalaryLog;

@Service
public class SalaryLogAOImpl implements ISalaryLogAO {

    @Autowired
    private ISalaryLogBO salaryLogBO;

    @Override
    public String addSalaryLog(SalaryLog data) {
        return salaryLogBO.saveSalaryLog(data);
    }

    @Override
    public void editSalaryLog(SalaryLog data) {
    }

    @Override
    public void dropSalaryLog(String code) {
    }

    @Override
    public Paginable<SalaryLog> querySalaryLogPage(int start, int limit,
            SalaryLog condition) {
        return salaryLogBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<SalaryLog> querySalaryLogList(SalaryLog condition) {
        return salaryLogBO.querySalaryLogList(condition);
    }

    @Override
    public SalaryLog getSalaryLog(String code) {
        return salaryLogBO.getSalaryLog(code);
    }
}
