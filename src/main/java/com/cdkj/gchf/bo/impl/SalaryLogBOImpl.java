package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.ISalaryLogBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.ISalaryLogDAO;
import com.cdkj.gchf.domain.Salary;
import com.cdkj.gchf.domain.SalaryLog;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;

@Component
public class SalaryLogBOImpl extends PaginableBOImpl<SalaryLog>
        implements ISalaryLogBO {

    @Autowired
    private ISalaryLogDAO salaryLogDAO;

    public String saveSalaryLog(SalaryLog data) {
        String code = null;
        if (data != null) {
            data.setCode(code);
            salaryLogDAO.insert(data);
        }
        return code;
    }

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
    public void saveSalaryLog(Salary salary, String companyCode,
            String companyName, String type, String handler,
            String handleNote) {
        SalaryLog data = new SalaryLog();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.SalaryLog.getCode());
        data.setCode(code);
        data.setSalaryCode(salary.getCode());
        data.setType(type);
        data.setCompanyCode(companyCode);
        data.setCompanyName(companyName);

        data.setStaffCode(salary.getStaffCode());
        data.setProjectCode(salary.getProjectCode());
        data.setProjectName(salary.getProjectName());
        data.setHandler(handler);
        data.setHandleDatetime(new Date());
        data.setHandleNote(handleNote);
        salaryLogDAO.insert(data);
    }
}
