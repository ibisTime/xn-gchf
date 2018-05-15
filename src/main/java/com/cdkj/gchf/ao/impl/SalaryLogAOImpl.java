package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.ISalaryLogAO;
import com.cdkj.gchf.bo.ISalaryLogBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Page;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.SalaryLog;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.enums.EUser;
import com.cdkj.gchf.enums.EUserKind;

@Service
public class SalaryLogAOImpl implements ISalaryLogAO {

    @Autowired
    private ISalaryLogBO salaryLogBO;

    @Autowired
    private IUserBO userBO;

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
        System.out.println(condition.getProjectCodeList());
        Paginable<SalaryLog> page = new Page<SalaryLog>();
        List<SalaryLog> list = new ArrayList<SalaryLog>();
        if (EUserKind.Supervise.getCode().equals(condition.getKind())) {
            if (CollectionUtils.isEmpty(condition.getProjectCodeList())) {
                page.setList(list);
                return page;
            }
        }

        page = salaryLogBO.getPaginable(start, limit, condition);
        for (SalaryLog salaryLog : page.getList()) {
            salaryLog.setHandleName(getName(salaryLog.getHandler()));
            salaryLog.setStaffName(getName(salaryLog.getStaffCode()));
        }
        return page;
    }

    @Override
    public List<SalaryLog> querySalaryLogList(SalaryLog condition) {
        List<SalaryLog> list = new ArrayList<SalaryLog>();
        if (EUserKind.Supervise.getCode().equals(condition.getKind())) {
            if (CollectionUtils.isEmpty(condition.getProjectCodeList())) {
                return list;
            }
        }

        list = salaryLogBO.querySalaryLogList(condition);
        String handleName = null;
        for (SalaryLog salaryLog : list) {
            handleName = getName(salaryLog.getHandler());
            salaryLog.setHandleName(handleName);
            salaryLog.setStaffName(getName(salaryLog.getStaffCode()));
        }
        return list;
    }

    @Override
    public SalaryLog getSalaryLog(String code) {
        SalaryLog data = salaryLogBO.getSalaryLog(code);
        String handleName = getName(data.getHandler());
        data.setHandleName(handleName);
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
