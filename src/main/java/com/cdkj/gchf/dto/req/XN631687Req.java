package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631687Req extends AListReq {
    /**   
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = 4649661256910558174L;

    // 合同类型
    private String contractPeriodType;

    // 项目编码
    private String projectCode;

    // 所属企业统一社会信用代码
    private String corpCode;

    // 上传状态
    private String uploadStatus;

    @NotBlank
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
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

    public String getContractPeriodType() {
        return contractPeriodType;
    }

    public void setContractPeriodType(String contractPeriodType) {
        this.contractPeriodType = contractPeriodType;
    }

    public String getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

}
