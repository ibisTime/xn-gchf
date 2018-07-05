package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Message;

public interface IMessageDAO extends IBaseDAO<Message> {
    String NAMESPACE = IMessageDAO.class.getName().concat(".");

    void update(Message data);

    // 删除工资条时更新代发消息
    public int update4DropSalary(Message data);

    void sendMessage(Message data);

    void approveMessage(Message data);

    void downLoad(Message data);

    // 当不存在工资条时删除代发消息
    public int deleteNotExistSalary(Message data);
}
