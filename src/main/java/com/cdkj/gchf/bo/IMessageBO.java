package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Message;

public interface IMessageBO extends IPaginableBO<Message> {

    public void saveMessage(Message data);

    public void removeMessage(String code);

    public void refreshMessage(Message data);

    public List<Message> queryMessageList(Message condition);

    public Message getMessage(String code);

    public void sendMessage(Message data);

    public void approveMessage(Message data, String handler, String handleNote);

    public void downLoad(Message data);

}
