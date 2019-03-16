package com.cdkj.gchf.dto.req;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.cdkj.gchf.gov.APIRequestBase;

/**
 * 上传项目人员
 * @author: silver 
 * @since: Mar 13, 2019 1:34:10 PM 
 * @history:
 */
public class XN631911ReqWorker extends APIRequestBase {

    private static final long serialVersionUID = 8006164993445757938L;

    // 工人姓名
    @NotBlank
    private String workerName;

    // 是否班组长
    @NotBlank
    private Integer isTeamLeader;

    // 证件类型
    @NotBlank
    private String idcardType;

    // 证件号码
    @NotBlank
    private String idcardNumber;

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

    public String getIdcardType() {
        return idcardType;
    }

    public void setIdcardType(String idcardType) {
        this.idcardType = idcardType;
    }

    public String getIdcardNumber() {
        return idcardNumber;
    }

    public void setIdcardNumber(String idcardNumber) {
        this.idcardNumber = idcardNumber;
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
