package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.ISalaryLogAO;
import com.cdkj.gchf.bo.ISalaryBO;
import com.cdkj.gchf.bo.ISalaryLogBO;
import com.cdkj.gchf.bo.IStaffBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Salary;
import com.cdkj.gchf.domain.SalaryLog;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.enums.EUser;

@Service
public class SalaryLogAOImpl implements ISalaryLogAO {

    @Autowired
    private ISalaryLogBO salaryLogBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ISalaryBO salaryBO;

    @Autowired
    private IStaffBO staffBO;

    @Override
    public void dealWithSalary(String code, String handler, String handleNote) {
        SalaryLog data = salaryLogBO.getSalaryLog(code);
        salaryLogBO.dealWithSalary(data, handler, handleNote);
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
        Paginable<SalaryLog> page = salaryLogBO.getPaginable(start, limit,
            condition);
        Staff staff = null;
        for (SalaryLog salaryLog : page.getList()) {
            staff = staffBO.getStaff(salaryLog.getSalaryCode());
            salaryLog.setHandleName(getName(salaryLog.getHandler()));
            salaryLog.setStaffName(staff.getName());
        }
        return page;
    }

    @Override
    public List<SalaryLog> querySalaryLogList(SalaryLog condition) {
        List<SalaryLog> list = salaryLogBO.querySalaryLogList(condition);
        Staff staff = null;
        for (SalaryLog salaryLog : list) {
            staff = staffBO.getStaff(salaryLog.getStaffCode());
            salaryLog.setHandleName(getName(salaryLog.getHandler()));
            salaryLog.setStaffName(staff.getName());
        }
        return list;
    }

    @Override
    public SalaryLog getSalaryLog(String code) {
        SalaryLog data = salaryLogBO.getSalaryLog(code);
        Salary salary = salaryBO.getSalary(data.getSalaryCode());
        data.setSalary(salary);
        String handleName = getName(data.getHandler());
        data.setHandleName(handleName);
        Staff staff = staffBO.getStaff(data.getSalaryCode());
        data.setStaffName(staff.getName());
        return data;
    }

    private String getName(String userId) {
        User user = userBO.getUserName(userId);
        String name = null;
        if (user != null) {
            name = EUser.ADMIN.getCode();
            if (!EUser.ADMIN.getCode().equals(user.getLoginName())) {
                name = user.getRealName();
            }
        }
        return name;

    }
}
