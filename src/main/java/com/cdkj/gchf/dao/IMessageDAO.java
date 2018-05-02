package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Message;

public interface IMessageDAO extends IBaseDAO<Message> {
    String NAMESPACE = IMessageDAO.class.getName().concat(".");

    void update(Message data);

    void sendMessage(Message data);

    void approveMessage(Message data);

    void downLoad(Message data);
}
