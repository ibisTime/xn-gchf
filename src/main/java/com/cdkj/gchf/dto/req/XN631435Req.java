package com.cdkj.gchf.dto.req;

import java.util.List;

/**
 * 分页查代发消息
 * @author: nyc 
 * @since: 2018年5月1日 上午11:48:15 
 * @history:
 */
public class XN631435Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 6478578711244877650L;

    // （选填）工程编号
    private String projectCode;

    // （选填）工程名称
    private String projectName;

    // （选填）发送人
    private String sender;

    // （选填）状态
    private String status;

    // （选填）状态List
    private List<String> statusList;

    // （选填）处理人
    private String handler;

    // （选填）关键字
    private String keyword;

    // （选填）用户类型
    private String kind;

    // （选填）银行名称
    private String bankName;

    // （选填）户名
    private String subbranch;

    // （选填）项目编号List
    private List<String> projectCodeList;

    // 工资所属月份
    private String month;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSubbranch() {
        return subbranch;
    }

    public void setSubbranch(String subbranch) {
        this.subbranch = subbranch;
    }

    public List<String> getProjectCodeList() {
        return projectCodeList;
    }

    public void setProjectCodeList(List<String> projectCodeList) {
        this.projectCodeList = projectCodeList;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

}
