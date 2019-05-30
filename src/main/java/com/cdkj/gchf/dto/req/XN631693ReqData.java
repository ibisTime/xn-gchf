package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631693ReqData {
    // 企业统一社会信用代码
    @NotBlank(message = "企业统一社会信用代码不能为空")
    private String corpCode;

    // 班组所在企业名称
    @NotBlank(message = "企业名称不能为空")
    private String corpName;

    // 班组名称
    @NotBlank(message = "班组名称不能为空")
    private String teamName;

    // 工人姓名
    @NotBlank(message = "人员名称不能为空")
    private String workerName;

    // 是否班组长
    private String isTeamLeader;

    // 证件号码
    @NotBlank
    private String idCardNumber;

    // 工种
    @NotBlank
    private String workType;

    // 工人类型
    private Integer workRole;

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
    private String hasBuyInsurance;

    // 民族
    @NotBlank
    private String nation;

    // 地址
    @NotBlank
    private String address;

    // 政治面貌
    private String politicsType;

    // 加入公会时间
    private String joinedTime;

    // 手机号码
    @NotBlank
    private String cellPhone;

    // 文化程度
    @NotBlank
    private String cultureLevelType;

    // 特长
    private String specialty;

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

    // 证件有效期开始日期
    @NotBlank
    private String startDate;

    // 证件有效期结束日期
    @NotBlank
    private String expiryDate;

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

    public String getIsTeamLeader() {
        return isTeamLeader;
    }

    public void setIsTeamLeader(String isTeamLeader) {
        this.isTeamLeader = isTeamLeader;
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

    public String getHasBuyInsurance() {
        return hasBuyInsurance;
    }

    public void setHasBuyInsurance(String hasBuyInsurance) {
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

    public String getPoliticsType() {
        return politicsType;
    }

    public void setPoliticsType(String politicsType) {
        this.politicsType = politicsType;
    }

    public String getJoinedTime() {
        return joinedTime;
    }

    public void setJoinedTime(String joinedTime) {
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
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
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

}
