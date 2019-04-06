package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631690Req {
    // 用户id
    @NotBlank
    private String userId;

    // 项目编码
    @NotBlank(message = "项目编码不能为空")
    private String projectCode;

    // 企业统一社会信用代码
    @NotBlank(message = "企业统一社会信用代码不能为空")
    private String corpCode;

    // 班组编号
    @NotBlank(message = "班组编号不能为空")
    private String teamSysNo;

    // 工人编号
    @NotBlank(message = "人员编号不能为空")
    private String workerCode;

    // 是否班组长
    private String isTeamLeader;

    // 当前工种
    @NotBlank
    private String workType;

    // 工人类型
    private String workRole;

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

    // 开始工作日期
    private String workDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getTeamSysNo() {
        return teamSysNo;
    }

    public void setTeamSysNo(String teamSysNo) {
        this.teamSysNo = teamSysNo;
    }

    public String getWorkerCode() {
        return workerCode;
    }

    public void setWorkerCode(String workerCode) {
        this.workerCode = workerCode;
    }

    public String getIsTeamLeader() {
        return isTeamLeader;
    }

    public void setIsTeamLeader(String isTeamLeader) {
        this.isTeamLeader = isTeamLeader;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
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

    public String getWorkRole() {
        return workRole;
    }

    public void setWorkRole(String workRole) {
        this.workRole = workRole;
    }

    public String getWorkDate() {
        return workDate;
    }

}
