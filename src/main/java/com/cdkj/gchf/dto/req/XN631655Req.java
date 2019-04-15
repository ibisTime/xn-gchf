package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

public class XN631655Req {
    @NotBlank
    // 班组编号
    private String code;

    @NotBlank(message = "班组信息未上传，无法修改")
    // 国家平台班组编号
    private String teamSysNo;

    // 项目编号
    @NotBlank(message = "项目编号不能为空")
    private String projectCode;

    // 责任人名称
    private String responsiblePersonName;

    // 责任人手机号
    private String responsiblePersonPhone;

    // 责任人证件类型
    private String responsiblePersonIDCardType;

    // 责任人证件号码
    private String responsiblePersonIDNumber;

    // 备注
    private String remark;

    // 进场时间
    private String entryTime;

    // 退场时间
    private String exitTime;

    // 进场附件列表
    private List<XN631655ReqEntryAttachments> entryAttachments;

    // 退场附件列表
    private List<XN631655ReqExitAttachments> exitAttachments;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getTeamSysNo() {
        return teamSysNo;
    }

    public void setTeamSysNo(String teamSysNo) {
        this.teamSysNo = teamSysNo;
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

    public List<XN631655ReqEntryAttachments> getEntryAttachments() {
        return entryAttachments;
    }

    public void setEntryAttachments(
            List<XN631655ReqEntryAttachments> entryAttachments) {
        this.entryAttachments = entryAttachments;
    }

    public List<XN631655ReqExitAttachments> getExitAttachments() {
        return exitAttachments;
    }

    public void setExitAttachments(
            List<XN631655ReqExitAttachments> exitAttachments) {
        this.exitAttachments = exitAttachments;
    }

}
