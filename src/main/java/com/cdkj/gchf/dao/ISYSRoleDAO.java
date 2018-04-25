package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.SYSRole;

public interface ISYSRoleDAO extends IBaseDAO<SYSRole> {
    String NAMESPACE = ISYSRoleDAO.class.getName().concat(".");

    public int update(SYSRole data);
}
