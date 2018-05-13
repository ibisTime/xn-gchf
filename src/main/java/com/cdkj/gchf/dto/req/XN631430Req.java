package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 发送代发消息
 * @author: nyc 
 * @since: 2018年5月1日 上午11:48:15 
 * @history:
 */
public class XN631430Req {

    // 编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // 标题
    @NotBlank(message = "标题不能为空")
    private String title;

    // 内容
    @NotBlank(message = "内容不能为空")
    private String content;

    // 发送人
    @NotBlank(message = "发送人不能为空")
    private String sender;

    // 发送备注
    private String sendNote;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSendNote() {
        return sendNote;
    }

    public void setSendNote(String sendNote) {
        this.sendNote = sendNote;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
