package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Department;

public interface IDepartmentBO extends IPaginableBO<Department> {

    public void saveDepartment(Department data);

    public void removeDepartment(Department data);

    public void refreshDepartment(Department data);

    public List<Department> queryDepartmentList(Department condition);

    public Department getDepartment(String code);

}
