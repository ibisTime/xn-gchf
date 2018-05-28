package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.AbnormalRemind;

public interface IAbnormalRemindDAO extends IBaseDAO<AbnormalRemind> {

    String NAMESPACE = IAbnormalRemindDAO.class.getName().concat(".");

    void update(AbnormalRemind data);
}
