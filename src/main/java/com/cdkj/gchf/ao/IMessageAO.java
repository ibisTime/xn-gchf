package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Message;
import com.cdkj.gchf.dto.req.XN631439Req;

@Component
public interface IMessageAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 发送消息
    public void sendMessage(String code, String title, String sender,
            String sendNote);

    // 反馈消息
    public void approveMessage(String code, String handler, String handleNote,
            List<XN631439Req> list);

    // 下载
    public Message downLoad(String userId, String code, String download,
            String backDownload);

    public Paginable<Message> queryMessagePage(int start, int limit,
            Message condition);

    public List<Message> queryMessageList(Message condition);

    public Message getMessage(String code);

}
