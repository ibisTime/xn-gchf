package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.EventRemind;

public interface IEventRemindDAO extends IBaseDAO<EventRemind> {

    String NAMESPACE = IEventRemindDAO.class.getName().concat(".");

    void update(EventRemind data);
}
