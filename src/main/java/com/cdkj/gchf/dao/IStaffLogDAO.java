package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.StaffLog;

//dao层 
public interface IStaffLogDAO extends IBaseDAO<StaffLog> {
	String NAMESPACE = IStaffLogDAO.class.getName().concat(".");
}