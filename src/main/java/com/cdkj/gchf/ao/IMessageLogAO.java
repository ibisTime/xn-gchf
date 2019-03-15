package com.cdkj.gchf.ao;

import java.util.List;

import com.cdkj.gchf.domain.MessageLog;

public interface IMessageLogAO {

    List<MessageLog> queryMessageLogList(MessageLog mCondition);

}
