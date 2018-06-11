package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Staff;

//dao层 
public interface IStaffDAO extends IBaseDAO<Staff> {
    String NAMESPACE = IStaffDAO.class.getName().concat(".");

    void update(Staff data);

    void insertStaffInfo(Staff data);

    void updateSalaryStatus(Staff data);

    void doDepartmentCode(Staff data);

    void allotDepartment(Staff data);
}
