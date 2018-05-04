package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Report;

//dao层 
public interface IReportDAO extends IBaseDAO<Report> {
	String NAMESPACE = IReportDAO.class.getName().concat(".");
}