package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Department;

//dao层 
public interface IDepartmentDAO extends IBaseDAO<Department> {
    String NAMESPACE = IDepartmentDAO.class.getName().concat(".");

    public int update(Department data);
}
