package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IMessageAO;
import com.cdkj.gchf.bo.IMessageBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Message;

@Service
public class MessageAOImpl implements IMessageAO {

    @Autowired
    private IMessageBO messageBO;

    @Override
    public String addMessage(Message data) {
        messageBO.saveMessage(data);
        return null;
    }

    @Override
    public void editMessage(Message data) {
    }

    @Override
    public void dropMessage(String code) {
    }

    @Override
    public Paginable<Message> queryMessagePage(int start, int limit,
            Message condition) {
        return messageBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Message> queryMessageList(Message condition) {
        return messageBO.queryMessageList(condition);
    }

    @Override
    public Message getMessage(String code) {
        return messageBO.getMessage(code);
    }

    @Override
    public void sendMessage(String code, String sender, String sendNote) {
        Message data = messageBO.getMessage(code);
        messageBO.sendMessage(data, sender, sendNote);
    }

    @Override
    public void approveMessage(String code, String handler, String handleNote) {
        Message data = messageBO.getMessage(code);
        messageBO.approveMessage(data, handler, handleNote);
    }

    @Override
    public void downLoad(String code) {
        Message data = messageBO.getMessage(code);
        if (data.getDownload() == null) {
            data.setDownload(1);
        } else {
            data.setDownload(data.getDownload() + 1);
        }
        messageBO.downLoad(data);
    }
}
