package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.ISalaryLogAO;
import com.cdkj.gchf.bo.ISalaryBO;
import com.cdkj.gchf.bo.ISalaryLogBO;
import com.cdkj.gchf.bo.IStaffBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.domain.Salary;
import com.cdkj.gchf.domain.SalaryLog;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.ESalaryLogType;
import com.cdkj.gchf.enums.ESalaryStatus;
import com.cdkj.gchf.enums.EUser;
import com.cdkj.gchf.exception.BizException;

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
    public String addsalaryLog(String salaryCode, String handler,
            String handleNote) {

        SalaryLog data = new SalaryLog();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.SalaryLog.getCode());
        Salary salary = salaryBO.getSalary(salaryCode);
        data.setCode(code);
        data.setSalaryCode(salary.getCode());

        data.setStaffCode(salary.getStaffCode());
        data.setType(ESalaryLogType.Abnormal.getCode());
        data.setHandler(handler);
        Date date = new Date();
        data.setHandleDatetime(date);

        data.setHandleNote(handleNote);
        salaryLogBO.saveSalaryLog(data);
        // 查看员工是否还有未发工资
        return code;
    }

    @Override
    public String changeToNormal(String salaryCode, String handler,
            String handleNote) {

        SalaryLog data = new SalaryLog();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.SalaryLog.getCode());
        Salary salary = salaryBO.getSalary(salaryCode);
        if (ESalaryStatus.Pay_Again.getCode().equals(salary.getStatus())) {
            throw new BizException("xn00000", "该工资条已转正常，请勿重复操作");
        }

        data.setCode(code);
        data.setSalaryCode(salary.getCode());
        data.setStaffCode(salary.getStaffCode());
        data.setType(ESalaryLogType.Normal.getCode());
        data.setHandler(handler);

        Date date = new Date();
        data.setHandleDatetime(date);
        data.setHandleNote(handleNote);
        salaryLogBO.saveSalaryLog(data);

        // 改变工资条状态
        salary.setStatus(ESalaryStatus.Pay_Again.getCode());
        salaryBO.refreshStatus(salary);
        return code;
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
