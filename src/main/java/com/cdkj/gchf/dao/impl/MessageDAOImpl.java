package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IMessageDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.Message;

@Repository("messageDAOImpl")
public class MessageDAOImpl extends AMybatisTemplate implements IMessageDAO {

    @Override
    public int insert(Message data) {
        return super.insert(NAMESPACE.concat("insert_message"), data);
    }

    @Override
    public int delete(Message data) {
        return super.delete(NAMESPACE.concat("delete_message"), data);
    }

    @Override
    public Message select(Message condition) {
        return super.select(NAMESPACE.concat("select_message"), condition,
            Message.class);
    }

    @Override
    public long selectTotalCount(Message condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_message_count"),
            condition);
    }

    @Override
    public List<Message> selectList(Message condition) {
        return super.selectList(NAMESPACE.concat("select_message"), condition,
            Message.class);
    }

    @Override
    public List<Message> selectList(Message condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_message"), start,
            count, condition, Message.class);
    }

    @Override
    public void update(Message data) {
        super.update(NAMESPACE.concat("update_message"), data);
    }

    @Override
    public void sendMessage(Message data) {
        super.update(NAMESPACE.concat("send_message"), data);
    }

    @Override
    public void approveMessage(Message data) {
        super.update(NAMESPACE.concat("approve_message"), data);
    }

    @Override
    public void downLoad(Message data) {
        super.update(NAMESPACE.concat("down_load"), data);
    }

}
