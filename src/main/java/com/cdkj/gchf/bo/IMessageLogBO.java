package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.MessageLog;

public interface IMessageLogBO extends IPaginableBO<MessageLog> {

    List<MessageLog> queryMessageLogList(MessageLog condition);

}
