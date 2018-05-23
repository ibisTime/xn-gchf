package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IMessageLogAO;
import com.cdkj.gchf.bo.IMessageLogBO;
import com.cdkj.gchf.domain.MessageLog;

@Service
public class MessageLogAOImpl implements IMessageLogAO {

    @Autowired
    IMessageLogBO messageLogBO;

    @Override
    public List<MessageLog> queryMessageLogList(MessageLog condition) {
        return messageLogBO.queryMessageLogList(condition);
    }

}
