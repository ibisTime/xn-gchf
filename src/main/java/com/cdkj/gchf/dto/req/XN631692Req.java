package com.cdkj.gchf.dto.req;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

public class XN631692Req {
    // 用户id
    @NotBlank
    private String userId;

    @NotBlank
    private String code;

    // 项目编码
    private String projectCode;

    // 企业统一社会信用代码
    private String corpCode;

    // 企业名称
    @NotBlank
    private String corpName;

    // 班组编号
    private String teamSysNo;

    // 工人姓名
    private String workerName;

    // 是否班组长
    private Integer isTeamLeader;

    // 证件类型
    private String idCardType;

    // 证件号码
    private String idCardNumber;

    // 当前工种
    private String workType;

    // 工人类型
    private Integer workRole;

    // 发卡时间
    private String issueCardDate;

    // 办卡采集相片
    private String issueCardPic;

    // 考勤卡号
    private String cardNumber;

    // 发放工资银行卡号
    private String payRollBankCardNumber;

    // 发放工资银行名称
    private String payRollBankName;

    // 工资卡银行联号
    private String bankLinkNumber;

    // 工资卡银行
    private String payRollTopBankCode;

    // 是否购买工伤或意外伤害保险
    private String hasBuyInsurance;

    private Date workDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getTeamSysNo() {
        return teamSysNo;
    }

    public void setTeamSysNo(String teamSysNo) {
        this.teamSysNo = teamSysNo;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public Integer getIsTeamLeader() {
        return isTeamLeader;
    }

    public void setIsTeamLeader(Integer isTeamLeader) {
        this.isTeamLeader = isTeamLeader;
    }

    public String getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public Integer getWorkRole() {
        return workRole;
    }

    public void setWorkRole(Integer workRole) {
        this.workRole = workRole;
    }

    public String getIssueCardDate() {
        return issueCardDate;
    }

    public void setIssueCardDate(String issueCardDate) {
        this.issueCardDate = issueCardDate;
    }

    public String getIssueCardPic() {
        return issueCardPic;
    }

    public void setIssueCardPic(String issueCardPic) {
        this.issueCardPic = issueCardPic;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPayRollBankCardNumber() {
        return payRollBankCardNumber;
    }

    public void setPayRollBankCardNumber(String payRollBankCardNumber) {
        this.payRollBankCardNumber = payRollBankCardNumber;
    }

    public String getPayRollBankName() {
        return payRollBankName;
    }

    public void setPayRollBankName(String payRollBankName) {
        this.payRollBankName = payRollBankName;
    }

    public String getBankLinkNumber() {
        return bankLinkNumber;
    }

    public void setBankLinkNumber(String bankLinkNumber) {
        this.bankLinkNumber = bankLinkNumber;
    }

    public String getPayRollTopBankCode() {
        return payRollTopBankCode;
    }

    public void setPayRollTopBankCode(String payRollTopBankCode) {
        this.payRollTopBankCode = payRollTopBankCode;
    }

    public String getHasBuyInsurance() {
        return hasBuyInsurance;
    }

    public void setHasBuyInsurance(String hasBuyInsurance) {
        this.hasBuyInsurance = hasBuyInsurance;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

}
