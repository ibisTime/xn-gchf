package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631725Req extends APageReq {
    /**   
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = -7875111412189142071L;

    // 编号
    private String code;

    // 项目编码
    private String projectCode;

    // 企业唯一信用编码
    private String corpCode;

    // 企业名称
    private String corpName;

    // 班组编号（本地）
    private String teamSysNo;

    // 上传状态
    private String uploadStatus;

    // 工人姓名
    private String workerName;

    // 来源
    private String source;

    // 用户id
    @NotBlank
    private String userId;

    private String dateStartDatetime;

    private String dateEndDatetime;

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

    public String getTeamSysNo() {
        return teamSysNo;
    }

    public void setTeamSysNo(String teamSysNo) {
        this.teamSysNo = teamSysNo;
    }

    public String getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDateStartDatetime() {
        return dateStartDatetime;
    }

    public void setDateStartDatetime(String dateStartDatetime) {
        this.dateStartDatetime = dateStartDatetime;
    }

    public String getDateEndDatetime() {
        return dateEndDatetime;
    }

    public void setDateEndDatetime(String dateEndDatetime) {
        this.dateEndDatetime = dateEndDatetime;
    }
}
