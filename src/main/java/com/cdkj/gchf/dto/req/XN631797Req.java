package com.cdkj.gchf.dto.req;

/**
 * @author old3
 * @title: XN631797Req
 * @description: TODO
 * @date 2019-05-20 10:08
 */
public class XN631797Req {

    /**
     * code : 编号
     * phone : 手机号
     * politicsType : 政治面貌
     * cultureLevelType :  文化程度
     * isJoined :  是否加入公会
     * specialty : 特长
     * hasBadMedicalHistory : 是否有重大病史
     * maritalStatus : 婚姻状况
     * urgentLinkMan :  紧急联系人姓名
     * urgentLinkManPhone : 紧急联系电话
     * userId : 用户id
     */

    private String code;
    private String phone;
    private String politicsType;
    private String cultureLevelType;
    private String isJoined;
    private String specialty;
    private String hasBadMedicalHistory;
    private String maritalStatus;
    private String urgentLinkMan;
    private String urgentLinkManPhone;
    private String userId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
