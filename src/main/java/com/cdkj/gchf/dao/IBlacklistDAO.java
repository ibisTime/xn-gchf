package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Blacklist;

public interface IBlacklistDAO extends IBaseDAO<Blacklist> {
    String NAMESPACE = IBlacklistDAO.class.getName().concat(".");
}
