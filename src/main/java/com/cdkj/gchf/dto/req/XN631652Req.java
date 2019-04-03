package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631652Req {
    // 编号
    @NotBlank
    private String code;

    // id
    @NotBlank
    private String userId;

    // 班组名称
    private String teamName;

    // 责任人姓名，
    private String responsiblePersonName;

    // 责任人联系电话
    private String responsiblePersonPhone;

    // 责任人证件类型
    private String responsiblePersonIDCardType;

    // 责任人证件号码
    private String responsiblePersonIDNumber;

    // 进场日期
    private String entryTime;

    // 退场日期
    private String exitTime;

    // 备注
    private String remark;

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

    public String getResponsiblePersonIDCardType() {
        return responsiblePersonIDCardType;
    }

    public void setResponsiblePersonIDCardType(
            String responsiblePersonIDCardType) {
        this.responsiblePersonIDCardType = responsiblePersonIDCardType;
    }

    public String getResponsiblePersonIDNumber() {
        return responsiblePersonIDNumber;
    }

    public void setResponsiblePersonIDNumber(String responsiblePersonIDNumber) {
        this.responsiblePersonIDNumber = responsiblePersonIDNumber;
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

}
