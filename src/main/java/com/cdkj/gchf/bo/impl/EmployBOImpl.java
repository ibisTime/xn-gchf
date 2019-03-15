package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IDepartmentBO;
import com.cdkj.gchf.bo.IEmployBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.dao.IEmployDAO;
import com.cdkj.gchf.domain.Department;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.enums.EEmployStatus;
import com.cdkj.gchf.exception.BizException;

@Component
public class EmployBOImpl extends PaginableBOImpl<Employ> implements IEmployBO {

    @Autowired
    private IEmployDAO employDAO;

    @Autowired
    private IDepartmentBO departmentBO;

    @Autowired
    IProjectBO projectBO;

    public void joinIn(Employ data) {
        employDAO.insert(data);
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
        employDAO.updateLeaveOffice(data);
    }

    @Override
    public void toHoliday(String code, Date startDatetime, Integer leaveDays) {
        Employ data = getEmploy(code);
        data.setStartDatetime(startDatetime);
        data.setLastLeavingDays(leaveDays);
        data.setTotalLeavingDays(data.getTotalLeavingDays() + leaveDays);

        String status = EEmployStatus.Work.getCode();
        if (DateUtil.isIn(startDatetime,
            DateUtil.getRelativeDateOfDays(startDatetime, leaveDays))) {
            status = EEmployStatus.Hoilday.getCode();
        }

        data.setStatus(status);
        employDAO.updateHoliday(data);
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
    public void updateStaffMobile(String staffCode, String mobile) {
        Employ data = new Employ();
        data.setStaffCode(staffCode);
        data.setStaffMobile(mobile);
        employDAO.updateStaffMobile(data);
    }

    @Override
    public Employ getEmployByStaff(String staffCode, String projectCode) {
        Employ data = null;
        if (StringUtils.isNotBlank(staffCode)
                && StringUtils.isNotBlank(projectCode)) {
            Employ condition = new Employ();
            condition.setProjectCode(projectCode);
            condition.setStaffCode(staffCode);
            condition.setStatus(EEmployStatus.Not_Leave.getCode());
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
    public List<Employ> queryEmployManagerList(String projectCode) {
        Employ condition = new Employ();
        condition.setProjectCode(projectCode);
        return queryEmployList(condition);
    }

    @Override
    public List<Employ> queryEmployList(Employ condition) {
        List<Employ> list = employDAO.selectList(condition);

        if (CollectionUtils.isNotEmpty(list)) {
            for (Employ employ : list) {
                initEmploy(employ);
            }
        }
        return list;
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

            initEmploy(data);
        }
        return data;
    }

    @Override
    public long getTotalCount(Employ condition) {
        return employDAO.selectTotalCount(condition);
    }

    private void initEmploy(Employ employ) {
        // 部门信息
        Department department = departmentBO
            .getDepartment(employ.getDepartmentCode());
        if (null != department) {
            employ.setDepartmentName(department.getName());
            employ.setDepartmentLeader(department.getLeader());
            employ.setDepartmentLeaderMobile(department.getLeadeMobile());
        }

        // 项目信息
        Project project = projectBO.getProject(employ.getProjectCode());
        if (null != project) {
            employ.setCompanyName(project.getCompanyName());
        }
    }

}
