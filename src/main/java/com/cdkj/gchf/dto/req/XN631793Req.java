package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631793Req {
    // 编号
    private String code;

    // 用户id
    @NotBlank
    private String userId;

    // 工人姓名
    @NotBlank
    private String name;

    // 工人性别
    @NotBlank
    private String gender;

    // 民族
    @NotBlank
    private String nation;

    // 出生日期
    @NotBlank
    private String birthday;

    // 手机号码
    @NotBlank
    private String cellPhone;

    // 证件类型
    @NotBlank
    private String idCardType;

    // 证件号码
    @NotBlank
    private String idCardNumber;

    // 正面照 URL
    private String positiveIdCardImageUrl;

    // 反面照 URL
    private String negativeIdCardImageUrl;

    // 手持身份证照片
    private String handIdCardImageUrl;

    // 有效期开始日期
    @NotBlank
    private String startDate;

    // 有效期结束日期
    @NotBlank
    private String expiryDate;

    // 住址
    @NotBlank
    private String address;

    // 发证机关
    @NotBlank
    private String grantOrg;

    // 政治面貌
    @NotBlank
    private String politicsType;

    // 文化程度
    @NotBlank
    private String cultureLevelType;

    // 头像
    @NotBlank
    private String headImageUrl;

    // 是否加入公会
    private String isJoined;

    // 加入公会时间
    private String joinedTime;

    // 特长
    private String specialty;

    // 是否有重大病历
    private String hasBadMedicalHistory;

    // 婚姻状况
    private String maritalStatus;

    // 紧急联系人姓名
    private String urgentLinkMan;

    // 紧急联系电话
    private String urgentLinkManPhone;

    // 档案创建时间
    private String createDatetime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGrantOrg() {
        return grantOrg;
    }

    public void setGrantOrg(String grantOrg) {
        this.grantOrg = grantOrg;
    }

    public String getPoliticsType() {
        return politicsType;
    }

    public void setPoliticsType(String politicsType) {
        this.politicsType = politicsType;
    }

    public String getCultureLevelType() {
        return cultureLevelType;
    }

    public void setCultureLevelType(String cultureLevelType) {
        this.cultureLevelType = cultureLevelType;
    }

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }

    public String getIsJoined() {
        return isJoined;
    }

    public void setIsJoined(String isJoined) {
        this.isJoined = isJoined;
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

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public String getJoinedTime() {
        return joinedTime;
    }

    public void setJoinedTime(String joinedTime) {
        this.joinedTime = joinedTime;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
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

    public String getHandIdCardImageUrl() {
        return handIdCardImageUrl;
    }

    public void setHandIdCardImageUrl(String handIdCardImageUrl) {
        this.handIdCardImageUrl = handIdCardImageUrl;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
