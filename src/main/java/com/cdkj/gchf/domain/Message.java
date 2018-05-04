package com.cdkj.gchf.domain;

import java.util.Date;

import com.cdkj.gchf.dao.base.ABaseDO;

/**
 * 代发消息
 * @author: nyc 
 * @since: 2018年5月1日 上午11:31:30 
 * @history:
 */

public class Message extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 项目编号
    private String projectCode;

    // 项目名称
    private String projectName;

    // 银行行别
    private String bankCode;

    // 银行名称
    private String bankName;

    // 开户行
    private String subbranch;

    // 银行卡号
    private String bankcardNumber;

    // 标题
    private String title;

    // 消息内容
    private String content;

    // 创建时间
    private Date createDatetime;

    // 下载次数
    private Integer download;

    // 状态
    private String status;

    // 发送人
    private String sender;

    // 发送时间
    private Date sendDatetime;

    // 发送说明
    private String sendNote;

    // 处理人
    private String handler;

    // 处理时间
    private Date handleDatetime;

    // 处理说明
    private String handleNote;

    // **************db**************

    private String keyword;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setSubbranch(String subbranch) {
        this.subbranch = subbranch;
    }

    public String getSubbranch() {
        return subbranch;
    }

    public void setBankcardNumber(String bankcardNumber) {
        this.bankcardNumber = bankcardNumber;
    }

    public String getBankcardNumber() {
        return bankcardNumber;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Integer getDownload() {
        return download;
    }

    public void setDownload(Integer download) {
        this.download = download;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }

    public void setSendNote(String sendNote) {
        this.sendNote = sendNote;
    }

    public String getSendNote() {
        return sendNote;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getHandler() {
        return handler;
    }

    public Date getSendDatetime() {
        return sendDatetime;
    }

    public void setSendDatetime(Date sendDatetime) {
        this.sendDatetime = sendDatetime;
    }

    public Date getHandleDatetime() {
        return handleDatetime;
    }

    public void setHandleDatetime(Date handleDatetime) {
        this.handleDatetime = handleDatetime;
    }

    public void setHandleNote(String handleNote) {
        this.handleNote = handleNote;
    }

    public String getHandleNote() {
        return handleNote;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
