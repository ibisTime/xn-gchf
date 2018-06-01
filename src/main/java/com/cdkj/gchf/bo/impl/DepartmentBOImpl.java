package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IDepartmentBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.dao.IDepartmentDAO;
import com.cdkj.gchf.domain.Department;
import com.cdkj.gchf.exception.BizException;

@Component
public class DepartmentBOImpl extends PaginableBOImpl<Department>
        implements IDepartmentBO {

    @Autowired
    private IDepartmentDAO departmentDAO;

    @Override
    public void saveDepartment(Department data) {
        departmentDAO.insert(data);
    }

    @Override
    public void removeDepartment(Department data) {
        departmentDAO.delete(data);
    }

    @Override
    public void refreshDepartment(Department data) {
        departmentDAO.update(data);
    }

    @Override
    public List<Department> queryDepartmentList(Department condition) {
        return departmentDAO.selectList(condition);
    }

    @Override
    public Department getDepartment(String code) {
        Department data = null;
        if (StringUtils.isNotBlank(code)) {
            Department condition = new Department();
            condition.setCode(code);
            data = departmentDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "该部门不存在");
            }
        }
        return data;
    }

    @Override
    public String getParentName(String parentCode) {
        Department data = null;
        Department condition = new Department();
        condition.setCode(parentCode);
        data = departmentDAO.select(condition);
        if (data == null) {
            throw new BizException("xn0000", "该部门不存在");
        }
        return data.getName();
    }

    @Override
    public List<Department> isExsit(String code) {
        Department condition = new Department();
        condition.setParentCode(code);
        return departmentDAO.selectList(condition);
    }

    @Override
    public List<Department> getDepartmentByCompanyCode(String companyCode) {
        Department condition = new Department();
        condition.setCompanyCode(companyCode);
        return departmentDAO.selectList(condition);
    }
}