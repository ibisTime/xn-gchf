package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IEmployBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.dao.IEmployDAO;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.domain.Salary;
import com.cdkj.gchf.enums.EEmploytatus;
import com.cdkj.gchf.exception.BizException;

@Component
public class EmployBOImpl extends PaginableBOImpl<Employ> implements IEmployBO {

    @Autowired
    private IEmployDAO employDAO;

    public void joinIn(Employ data) {
        employDAO.insert(data);
    }

    @Override
    public void toHoliday(Employ data) {
        employDAO.toHoliday(data);
    }

    @Override
    public void leaveOffice(Employ data, String leavingDatetime, String updater,
            String remark) {
        data.setLeavingDatetime(DateUtil.strToDate(leavingDatetime,
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setStatus(EEmploytatus.Leave.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        employDAO.leaveOffice(data);
    }

    @Override
    public int removeEmploy(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Employ data = new Employ();
            data.setCode(code);
            count = employDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshEmploy(Employ data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = employDAO.update(data);
        }
        return count;
    }

    @Override
    public List<Employ> queryEmployList(Employ condition) {
        return employDAO.selectList(condition);
    }

    @Override
    public Employ getEmploy(String code) {
        Employ data = null;
        if (StringUtils.isNotBlank(code)) {
            Employ condition = new Employ();
            condition.setCode(code);
            data = employDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "该雇佣信息不存在");
            }
        }
        return data;
    }

    @Override
    public Employ getEmployStaff(String staffCode) {
        Employ data = null;
        if (StringUtils.isNotBlank(staffCode)) {
            Employ condition = new Employ();
            condition.setStaffCode(staffCode);
            data = employDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "该雇佣信息不存在");
            }
        }
        return data;
    }

    @Override
    public long getSalaryCount(Employ condition) {
        return employDAO.getSalaryCount(condition);
    }

    @Override
    public void updateStatus(Employ data) {
        employDAO.updateStatus(data);
    }

    @Override
    public void updateLeavingDays(Salary data) {
        employDAO.updateLeavingDays(data);
    }

    @Override
    public void isExist(String projectCode, String staffCode) {
        Employ data = null;
        if (StringUtils.isNotBlank(staffCode)
                && StringUtils.isNotBlank(projectCode)) {
            Employ condition = new Employ();
            condition.setProjectCode(projectCode);
            condition.setStaffCode(staffCode);
            data = employDAO.select(condition);
            if (data != null) {
                throw new BizException("xn00000", "该员工已入职");
            }
        }
    }

    @Override
    public Employ getEmployByStaff(String staffCode, String projectCode) {
        Employ data = null;
        if (StringUtils.isNotBlank(staffCode)
                && StringUtils.isNotBlank(projectCode)) {
            Employ condition = new Employ();
            condition.setProjectCode(projectCode);
            condition.setStaffCode(staffCode);
            condition.setStatus(EEmploytatus.Not_Leave.getCode());
            data = employDAO.select(condition);
            System.out.println(data);
        }
        return data;
    }

    @Override
    public void updateSalaryStatus(Employ data) {
        employDAO.updateSalaryStatus(data);
    }

}
