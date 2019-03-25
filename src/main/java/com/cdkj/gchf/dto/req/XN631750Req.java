package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631750Req {
    // 用户id
    @NotBlank(message = "用户id不能为空")
    private String userId;

    // 编号
    private String code;

    // 业务类型
    @NotBlank
    private String businessType;

    // 业务编号
    private Integer businessSysNo;

    // 银行支行名称
    @NotBlank
    private String bankName;

    // 银行账户
    @NotBlank
    private String bankNumber;

    // 银行联号
    @NotBlank
    private String bankLinkNumber;

    // 状态
    private String uploadStatus;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Integer getBusinessSysNo() {
        return businessSysNo;
    }

    public void setBusinessSysNo(Integer businessSysNo) {
        this.businessSysNo = businessSysNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public String getBankLinkNumber() {
        return bankLinkNumber;
    }

    public void setBankLinkNumber(String bankLinkNumber) {
        this.bankLinkNumber = bankLinkNumber;
    }

}
