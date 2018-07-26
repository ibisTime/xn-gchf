package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IDepartmentBO;
import com.cdkj.gchf.bo.IEmployBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.dao.IEmployDAO;
import com.cdkj.gchf.domain.Department;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.enums.EEmployStatus;
import com.cdkj.gchf.exception.BizException;

@Component
public class EmployBOImpl extends PaginableBOImpl<Employ> implements IEmployBO {

    @Autowired
    private IEmployDAO employDAO;

    @Autowired
    private IDepartmentBO departmentBO;

    public void joinIn(Employ data) {
        employDAO.insert(data);
    }

    @Override
    public void toHoliday(String staffCode, String projectCode,
            Date startDatetime, Integer leaveDays) {
        Employ data = getEmployByStaff(staffCode, projectCode);
        data.setStartDatetime(startDatetime);
        data.setLastLeavingDays(leaveDays);
        data.setTotalLeavingDays(data.getTotalLeavingDays() + leaveDays);
        data.setStatus(EEmployStatus.Hoilday.getCode());
        employDAO.toHoliday(data);
    }

    @Override
    public void leaveOffice(Employ data, String leavingDatetime, String updater,
            String remark) {
        data.setLeavingDatetime(DateUtil.strToDate(leavingDatetime,
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setStatus(EEmployStatus.Leave.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        employDAO.leaveOffice(data);
    }

    @Override
    public void editEmploy(Employ data) {
        employDAO.update(data);
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
    public void updateSalaryStatus(Employ data) {
        employDAO.updateSalaryStatus(data);
    }

    @Override
    public Employ getEmployByStaff(String staffCode, String projectCode) {
        Employ data = null;
        if (StringUtils.isNotBlank(staffCode)
                && StringUtils.isNotBlank(projectCode)) {
            Employ condition = new Employ();
            condition.setProjectCode(projectCode);
            condition.setStaffCode(staffCode);
            data = employDAO.select(condition);
            if (null != data) {
                initEmploy(data);
            }

        }
        return data;
    }

    @Override
    public List<Employ> queryEmployListByProject(String projectCode,
            String status) {
        Employ condition = new Employ();
        condition.setProjectCode(projectCode);
        condition.setStatus(status);
        return queryEmployList(condition);
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
    public void updateLeavingStatus(Employ employ) {
        employDAO.updateLeavingStatus(employ);
    }

    private void initEmploy(Employ employ) {
        // 部门名称
        Department department = departmentBO
            .getDepartment(employ.getDepartmentCode());
        employ.setDepartmentName(department.getName());
    }
}
