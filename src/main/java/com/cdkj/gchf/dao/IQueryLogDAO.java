package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.QueryLog;

public interface IQueryLogDAO extends IBaseDAO<QueryLog> {

    String NAMESPACE = IQueryLogDAO.class.getName().concat(".");
}
