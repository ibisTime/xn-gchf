package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631765Req extends APageReq {

    /**   
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = 4903614058256679620L;

    // userId
    @NotBlank
    private String userId;

    private String uploadStatus;

    private String status;

    // 业务类型
    private String businessType;

    // 业务编号
    private String businessSysNo;

    //
    private String businessName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessSysNo() {
        return businessSysNo;
    }

    public void setBusinessSysNo(String businessSysNo) {
        this.businessSysNo = businessSysNo;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

}
