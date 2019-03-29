package com.cdkj.gchf.dto.req;

import java.util.Date;

public class XN631653ReqData {
    // 班组编号
    private String teamSysNo;

    // 班组名称
    private String teamName;

    // 班组长姓名
    private String teamLeaderName;

    // 班组长联系电话
    private String teamLeaderPhone;

    // 班组长证件类型
    private String teamLeaderIdcardType;

    // 班组长证件号码
    private String teamLeaderIdNumber;

    // 责任人姓名
    private String responsiblePersonName;

    // 责任人联系电话
    private String responsiblePersonPhone;

    // 责任人证件类型
    private String responsiblePersonIdcardType;

    // 责任人证件号码
    private String responsiblePersonIdNumber;

    // 进场日期
    private Date entryTime;

    // 退场日期
    private Date exitTime;

    public String getTeamSysNo() {
        return teamSysNo;
    }

    public void setTeamSysNo(String teamSysNo) {
        this.teamSysNo = teamSysNo;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
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

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

}
