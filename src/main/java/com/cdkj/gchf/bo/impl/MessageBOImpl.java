package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IMessageBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.dao.IMessageDAO;
import com.cdkj.gchf.domain.Message;
import com.cdkj.gchf.enums.EMessageStatus;
import com.cdkj.gchf.exception.BizException;

@Component
public class MessageBOImpl extends PaginableBOImpl<Message>
        implements IMessageBO {

    @Autowired
    private IMessageDAO messageDAO;

    public void saveMessage(Message data) {
        messageDAO.insert(data);
    }

    @Override
    public void sendMessage(Message data) {

        messageDAO.sendMessage(data);

    }

    @Override
    public Message getMessage(String code) {
        Message data = null;
        if (StringUtils.isNotBlank(code)) {
            Message condition = new Message();
            condition.setCode(code);
            data = messageDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "代发消息不存在");
            }
        }
        return data;
    }

    @Override
    public void removeMessage(String code) {
    }

    @Override
    public void refreshMessage(Message data) {
        messageDAO.update(data);
    }

    @Override
    public List<Message> queryMessageList(Message condition) {
        return messageDAO.selectList(condition);
    }

    @Override
    public void approveMessage(Message data, String handler,
            String handleNote) {
        data.setHandler(handler);
        data.setHandleDatetime(new Date());
        data.setHandleNote(handleNote);
        data.setStatus(EMessageStatus.Approved.getCode());
        messageDAO.approveMessage(data);
    }

    @Override
    public void downLoad(Message data) {
        data.setStatus(EMessageStatus.TO_Feedback.getCode());
        messageDAO.downLoad(data);
    }

}
