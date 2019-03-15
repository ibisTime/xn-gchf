package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.MessageLog;

public interface IMessageLogDAO extends IBaseDAO<MessageLog> {

    String NAMESPACE = IMessageLogDAO.class.getName().concat(".");
}
