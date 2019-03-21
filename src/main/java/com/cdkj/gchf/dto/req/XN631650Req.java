package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631650Req {

    private String code;

    // id
    @NotBlank
    private String userId;

    // 项目编码
    @NotBlank
    private String projectCode;

    // 班组所在企业统一社会信用代码
    @NotBlank
    private String corpCode;

    // 班组所在企业名称
    private String corpName;

    // 班组编号
    private Integer teamSysNo;

    // 班组名称，
    @NotBlank
    private String teamName;

    // 责任人姓名
    private String responsiblePersonName;

    // 责任人联系电话
    private String responsiblePersonPhone;

    // 责任人证件类型
    private String responsiblePersonIdcardType;

    // 责任人证件号码
    private String responsiblePersonIdNumber;

    // 备注
    private String remark;

    // 进场日期
    private String entryTime;

    // 退场日期
    private String exitTime;

    // 班组长姓名
    private String teamLeaderName;

    // 班组长联系电话
    private String teamLeaderPhone;

    // 班组长证件类型
    private String teamLeaderIdcardType;

    // 班组长证件号码
    private String teamLeaderIdNumber;

    // 上级部门编号
    private String parentCode;

    // 是否上传
    private String uploadStatus;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    public String getResponsiblePersonName() {
        return responsiblePersonName;
    }

    public void setResponsiblePersonName(String responsiblePersonName) {
        this.responsiblePersonName = responsiblePersonName;
    }

    public String getResponsiblePersonPhone() {
        return responsiblePersonPhone;
    }

    public void setResponsiblePersonPhone(String responsiblePersonPhone) {
        this.responsiblePersonPhone = responsiblePersonPhone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }

    public Integer getTeamSysNo() {
        return teamSysNo;
    }

    public void setTeamSysNo(Integer teamSysNo) {
        this.teamSysNo = teamSysNo;
    }

    public String getTeamLeaderName() {
        return teamLeaderName;
    }

    public void setTeamLeaderName(String teamLeaderName) {
        this.teamLeaderName = teamLeaderName;
    }

    public String getTeamLeaderPhone() {
        return teamLeaderPhone;
    }

    public void setTeamLeaderPhone(String teamLeaderPhone) {
        this.teamLeaderPhone = teamLeaderPhone;
    }

    public String getTeamLeaderIdcardType() {
        return teamLeaderIdcardType;
    }

    public void setTeamLeaderIdcardType(String teamLeaderIdcardType) {
        this.teamLeaderIdcardType = teamLeaderIdcardType;
    }

    public String getTeamLeaderIdNumber() {
        return teamLeaderIdNumber;
    }

    public void setTeamLeaderIdNumber(String teamLeaderIdNumber) {
        this.teamLeaderIdNumber = teamLeaderIdNumber;
    }

    public String getResponsiblePersonIdcardType() {
        return responsiblePersonIdcardType;
    }

    public void setResponsiblePersonIdcardType(
            String responsiblePersonIdcardType) {
        this.responsiblePersonIdcardType = responsiblePersonIdcardType;
    }

    public String getResponsiblePersonIdNumber() {
        return responsiblePersonIdNumber;
    }

    public void setResponsiblePersonIdNumber(String responsiblePersonIdNumber) {
        this.responsiblePersonIdNumber = responsiblePersonIdNumber;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

}
