package com.cdkj.gchf.domain;

import java.util.Date;
import java.util.List;

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

    // 支行名称
    private String subbranch;

    // 银行卡号
    private String bankcardNumber;

    // 所属月份
    private String month;

    // 每月累积发薪（当月所有（员工的shouldAmount - 迟到早退扣款）+ 奖励金额 - 手动扣款）
    private Long totalAmount;

    // 每月领薪人数
    private Integer number;

    // 每月共计扣款（当月所有员工的迟到早退扣款 + 手动扣款）
    private Long totalCutAmount;

    // 每月共计税费
    private Long totalTax;

    // 标题
    private String title;

    // 创建时间
    private Date createDatetime;

    // 下载次数
    private Integer download;

    // 反馈消息下载次数
    private Integer backDownload;

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

    // 关键字
    private String keyword;

    // 发送人
    private String sendName;

    // 处理人名字
    private String handleName;

    // 用户类型
    private String kind;

    // 状态List
    private List<String> statusList;

    // 项目编号List
    private List<String> projectCodeList;

    // 公司账户
    private ProjectCard projectCard;

    // 消息所属项目负责人
    private String projectChargeUser;

    // 消息所属项目负责人手机号
    private String projectChargeUserMobile;

    // 扣款账户名称
    private String account;

    // 承建单位
    private String companyName;

    // 承建单位项目
    private String sendCompanyProject;

    public String getSendCompanyProject() {
        return sendCompanyProject;
    }

    public void setSendCompanyProject(String sendCompanyProject) {
        this.sendCompanyProject = sendCompanyProject;
    }

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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
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

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getHandleName() {
        return handleName;
    }

    public void setHandleName(String handleName) {
        this.handleName = handleName;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public Integer getBackDownload() {
        return backDownload;
    }

    public void setBackDownload(Integer backDownload) {
        this.backDownload = backDownload;
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

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getTotalCutAmount() {
        return totalCutAmount;
    }

    public void setTotalCutAmount(Long totalCutAmount) {
        this.totalCutAmount = totalCutAmount;
    }

    public Long getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(Long totalTax) {
        this.totalTax = totalTax;
    }

    public ProjectCard getProjectCard() {
        return projectCard;
    }

    public void setProjectCard(ProjectCard projectCard) {
        this.projectCard = projectCard;
    }

    public String getProjectChargeUser() {
        return projectChargeUser;
    }

    public void setProjectChargeUser(String projectChargeUser) {
        this.projectChargeUser = projectChargeUser;
    }

    public String getProjectChargeUserMobile() {
        return projectChargeUserMobile;
    }

    public void setProjectChargeUserMobile(String projectChargeUserMobile) {
        this.projectChargeUserMobile = projectChargeUserMobile;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
