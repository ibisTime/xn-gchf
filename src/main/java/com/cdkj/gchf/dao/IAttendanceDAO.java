package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Attendance;

//dao层 
public interface IAttendanceDAO extends IBaseDAO<Attendance> {
	String NAMESPACE = IAttendanceDAO.class.getName().concat(".");
}