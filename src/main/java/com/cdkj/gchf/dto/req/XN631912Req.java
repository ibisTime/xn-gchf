package com.cdkj.gchf.dto.req;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改项目人员
 * @author: silver 
 * @since: Mar 13, 2019 1:34:10 PM 
 * @history:
 */
public class XN631912Req {

    // 项目编码
    @NotBlank
    private String projectCode;

    // 班组所在企业统一社会信用代码
    @NotBlank
    private String corpCode;

    // 班组所在企业名称
    @NotBlank
    private String corpName;

    // 班组编号
    @NotBlank
    private Integer teamSysNo;

    // 班组名称
    @NotBlank
    private String teamName;

    // 工人姓名
    @NotBlank
    private String workerName;

    // 是否班组长
    @NotBlank
    private Integer isTeamLeader;

    // 证件类型
    @NotBlank
    private String idCardType;

    // 证件号码
    @NotBlank
    private String idCardNumber;

    // 工种
    @NotBlank
    private String workType;

    // 工人类型
    @NotBlank
    private Integer workerRole;

    // 制卡时间
    private Date issueCardDate;

    // 制卡采集照片
    private String issueCardPicUrl;

    // 考勤卡号
    private String cardNumber;

    // 发放工资银行卡号
    private String payRollBankCardNumber;

    // 发放工资银行名称
    private String payRollBankName;

    // 发放工资总行名称
    private String payRollTopBankName;

    // 工资卡银行联号
    private String bankLinkNumber;

    // 工资卡银行代码
    private String payRollTopBankCode;

    // 有无购买工伤或意外伤害保险
    private Integer hasBuyInsurance;

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

    public Integer getTeamSysNo() {
        return teamSysNo;
    }

    public void setTeamSysNo(Integer teamSysNo) {
        this.teamSysNo = teamSysNo;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
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

    public Integer getWorkerRole() {
        return workerRole;
    }

    public void setWorkerRole(Integer workerRole) {
        this.workerRole = workerRole;
    }

    public Date getIssueCardDate() {
        return issueCardDate;
    }

    public void setIssueCardDate(Date issueCardDate) {
        this.issueCardDate = issueCardDate;
    }

    public String getIssueCardPicUrl() {
        return issueCardPicUrl;
    }

    public void setIssueCardPicUrl(String issueCardPicUrl) {
        this.issueCardPicUrl = issueCardPicUrl;
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

    public String getPayRollTopBankName() {
        return payRollTopBankName;
    }

    public void setPayRollTopBankName(String payRollTopBankName) {
        this.payRollTopBankName = payRollTopBankName;
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

    public Integer getHasBuyInsurance() {
        return hasBuyInsurance;
    }

    public void setHasBuyInsurance(Integer hasBuyInsurance) {
        this.hasBuyInsurance = hasBuyInsurance;
    }

}
