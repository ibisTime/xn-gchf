package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.ISalaryLogBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.dao.ISalaryLogDAO;
import com.cdkj.gchf.domain.SalaryLog;
import com.cdkj.gchf.exception.BizException;

@Component
public class SalaryLogBOImpl extends PaginableBOImpl<SalaryLog>
        implements ISalaryLogBO {

    @Autowired
    private ISalaryLogDAO salaryLogDAO;

    @Override
    public void removeSalaryLog(String code) {
        SalaryLog data = new SalaryLog();
        data.setCode(code);
        salaryLogDAO.delete(data);
    }

    @Override
    public void refreshSalaryLog(SalaryLog data) {
        salaryLogDAO.update(data);
    }

    @Override
    public List<SalaryLog> querySalaryLogList(SalaryLog condition) {
        return salaryLogDAO.selectList(condition);
    }

    @Override
    public SalaryLog getSalaryLog(String code) {
        SalaryLog data = null;
        if (StringUtils.isNotBlank(code)) {
            SalaryLog condition = new SalaryLog();
            condition.setCode(code);
            data = salaryLogDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "工资条日志不存在");
            }
        }
        return data;
    }

    @Override
    public void saveSalaryLog(SalaryLog data) {
        salaryLogDAO.insert(data);
    }

    @Override
    public void dealWithSalary(SalaryLog data, String handler,
            String handleNote) {
        Date date = new Date();
        data.setHandler(handler);
        data.setHandleDatetime(date);
        data.setHandleNote(handleNote);
        salaryLogDAO.dealWithSalary(data);

    }
}
