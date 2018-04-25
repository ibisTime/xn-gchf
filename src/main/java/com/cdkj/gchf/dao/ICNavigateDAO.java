package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.CNavigate;

public interface ICNavigateDAO extends IBaseDAO<CNavigate> {
    String NAMESPACE = ICNavigateDAO.class.getName().concat(".");

    public int update(CNavigate data);

}
