package com.cdkj.gchf.dto.req;

import java.util.List;

/**
 * 分页查工资条
 * @author: nyc 
 * @since: 2018年5月1日 上午11:48:15 
 * @history:
 */
public class XN631445Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -3735724866853750864L;

    // （选填）审核人
    private String approver;

    // （选填）关键字
    private String keyword;

    // （选填）消息编号
    private String messageCode;

    // （选填）工资所属月份
    private String month;

    // （选填）工程编号
    private String projectCode;

    // （选填）工程名称
    private String projectName;

    // （选填）公司编号
    private String companyCode;

    // （选填）公司名称
    private String companyName;

    // （选填）员工编号
    private String staffCode;

    // （选填）状态
    private String status;

    // （选填）用户类型
    private String kind;

    // （选填）状态List
    private List<String> statusList;

    // （选填）项目编号List
    private List<String> projectCodeList;

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public List<String> getProjectCodeList() {
        return projectCodeList;
    }

    public void setProjectCodeList(List<String> projectCodeList) {
        this.projectCodeList = projectCodeList;
    }

}
