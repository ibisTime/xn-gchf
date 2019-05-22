package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class XN631770Req {
    // 用户id
    @NotBlank
    private String userId;

    // 工资单编码
    private String payRollCode;

    // 平台为项目分配的接入编码
    @NotBlank
    private String projectCode;

    // 工人所属企业统一社会信用代码
    @NotBlank
    private String corpCode;

    /**
     * 选择的项目人员编号
     */
    @NotBlank
    private String workerCode;

    // 工人所属企业名称
    private String corpName;

    // 平台为班组分配的接入编号
    @NotBlank
    private String teamSysNo;

    // 发放工资的月份
    private String payMonth;

    @NotEmpty
    private List<XN631770ReqDetail> detailList;

    private List<XN631770ReqAttachments> attachments;

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

    public String getTeamSysNo() {
        return teamSysNo;
    }

    public void setTeamSysNo(String teamSysNo) {
        this.teamSysNo = teamSysNo;
    }

    public String getPayMonth() {
        return payMonth;
    }

    public void setPayMonth(String payMonth) {
        this.payMonth = payMonth;
    }

    public List<XN631770ReqDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<XN631770ReqDetail> detailList) {
        this.detailList = detailList;
    }

    public List<XN631770ReqAttachments> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<XN631770ReqAttachments> attachments) {
        this.attachments = attachments;
    }

    public String getPayRollCode() {
        return payRollCode;
    }

    public void setPayRollCode(String payRollCode) {
        this.payRollCode = payRollCode;
    }

    public String getWorkerCode() {
        return workerCode;
    }

    public void setWorkerCode(String workerCode) {
        this.workerCode = workerCode;
    }
}
