package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IMessageLogBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.dao.IMessageLogDAO;
import com.cdkj.gchf.domain.MessageLog;

@Component
public class MessageLogBOImpl extends PaginableBOImpl<MessageLog>
        implements IMessageLogBO {

    @Autowired
    IMessageLogDAO messageLogDAO;

    @Override
    public List<MessageLog> queryMessageLogList(MessageLog condition) {
        return messageLogDAO.selectList(condition);
    }

}
