package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IMessageLogDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.MessageLog;

@Repository("messageLogDAOImpl")
public class MessageLogDAOImpl extends AMybatisTemplate
        implements IMessageLogDAO {

    @Override
    public int insert(MessageLog data) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int delete(MessageLog data) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public MessageLog select(MessageLog condition) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long selectTotalCount(MessageLog condition) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<MessageLog> selectList(MessageLog condition) {
        return super.selectList(NAMESPACE.concat("select_messageLog"),
            condition, MessageLog.class);
    }

    @Override
    public List<MessageLog> selectList(MessageLog condition, int start,
            int count) {
        // TODO Auto-generated method stub
        return null;
    }

}
