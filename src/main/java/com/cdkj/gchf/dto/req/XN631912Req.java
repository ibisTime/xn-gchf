package com.cdkj.gchf.dto.req;

import java.util.Date;

import javax.validation.constraints.Min;

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
    @Min(0)
    private Integer teamSysNo;

    // 班组名称
    @NotBlank
    private String teamName;

    // 工人姓名
    @NotBlank
    private String workerName;

    // 是否班组长
    @Min(0)
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
    @Min(0)
    private Integer workRole;

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

    private String positiveIdCardImageUrl;

    private String negativeIdCardImageUrl;

    // 民族
    @NotBlank
    private String nation;

    // 地址
    @NotBlank
    private String address;

    // 头像
    @NotBlank
    private String headImage;

    // 政治面貌
    @NotBlank
    private String politicsType;

    // 加入公会时间
    private Date joinedTime;

    // 手机号码
    @NotBlank
    private String cellPhone;

    // 文化程度
    private String cultureLevelType;

    // 特长
    private String Specialty;

    // 是否有重大病史
    private String hasBadMedicalHistory;

    // 紧急联系人姓名
    private String urgentLinkMan;

    // 紧急联系方式
    private String urgentLinkManPhone;

    // 开始工作日期
    private String workDate;

    // 婚姻状况
    private String maritalStatus;

    // 发证机关
    @NotBlank
    private String grantOrg;

    // 正面照。
    private String positiveIDCardImage;

    // 反面照。
    private String negativeIDCardImage;

    // 证件有效期开始日期
    private String startDate;

    // 证件有效期结束日期
    private String expiryDate;

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

    public Integer getWorkRole() {
        return workRole;
    }

    public void setWorkRole(Integer workRole) {
        this.workRole = workRole;
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

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getPoliticsType() {
        return politicsType;
    }

    public void setPoliticsType(String politicsType) {
        this.politicsType = politicsType;
    }

    public Date getJoinedTime() {
        return joinedTime;
    }

    public void setJoinedTime(Date joinedTime) {
        this.joinedTime = joinedTime;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getCultureLevelType() {
        return cultureLevelType;
    }

    public void setCultureLevelType(String cultureLevelType) {
        this.cultureLevelType = cultureLevelType;
    }

    public String getSpecialty() {
        return Specialty;
    }

    public void setSpecialty(String specialty) {
        Specialty = specialty;
    }

    public String getHasBadMedicalHistory() {
        return hasBadMedicalHistory;
    }

    public void setHasBadMedicalHistory(String hasBadMedicalHistory) {
        this.hasBadMedicalHistory = hasBadMedicalHistory;
    }

    public String getUrgentLinkMan() {
        return urgentLinkMan;
    }

    public void setUrgentLinkMan(String urgentLinkMan) {
        this.urgentLinkMan = urgentLinkMan;
    }

    public String getUrgentLinkManPhone() {
        return urgentLinkManPhone;
    }

    public void setUrgentLinkManPhone(String urgentLinkManPhone) {
        this.urgentLinkManPhone = urgentLinkManPhone;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getGrantOrg() {
        return grantOrg;
    }

    public void setGrantOrg(String grantOrg) {
        this.grantOrg = grantOrg;
    }

    public String getPositiveIDCardImage() {
        return positiveIDCardImage;
    }

    public void setPositiveIDCardImage(String positiveIDCardImage) {
        this.positiveIDCardImage = positiveIDCardImage;
    }

    public String getNegativeIDCardImage() {
        return negativeIDCardImage;
    }

    public void setNegativeIDCardImage(String negativeIDCardImage) {
        this.negativeIDCardImage = negativeIDCardImage;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getPositiveIdCardImageUrl() {
        return positiveIdCardImageUrl;
    }

    public void setPositiveIdCardImageUrl(String positiveIdCardImageUrl) {
        this.positiveIdCardImageUrl = positiveIdCardImageUrl;
    }

    public String getNegativeIdCardImageUrl() {
        return negativeIdCardImageUrl;
    }

    public void setNegativeIdCardImageUrl(String negativeIdCardImageUrl) {
        this.negativeIdCardImageUrl = negativeIdCardImageUrl;
    }

}
