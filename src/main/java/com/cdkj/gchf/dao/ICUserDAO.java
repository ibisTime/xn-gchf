package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.CUser;

public interface ICUserDAO extends IBaseDAO<CUser> {
    String NAMESPACE = ICUserDAO.class.getName().concat(".");

}
