package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631727Req extends AListReq {
    /**   
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = -4399740571913291554L;

    // 项目编码
    private String projectCode;

    // 企业统一社会信用代码
    private String corpCode;

    // 班组编号
    private String teamSysNo;

    // 上传状态
    private String uploadStatus;

    // userId
    @NotBlank
    private String userId;

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

}
