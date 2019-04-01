package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631791Req {
    // 用户id
    @NotBlank
    private String userId;

    // 编号
    @NotBlank
    private String code;

    // 正面照 URL
    @NotBlank
    private String positiveIdCardImageUrl;

    // 反面照 URL
    @NotBlank
    private String negativeIdCardImageUrl;

    // 身份证号
    @NotBlank
    private String idCardNumber;

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

    public String getPositiveIdCardImageUrl() {
        return positiveIdCardImageUrl;
    }

    public void setPositiveIdCardImageUrl(String positiveIdCardImageUrl) {
        this.positiveIdCardImageUrl = positiveIdCardImageUrl;
    }

    public String getNegativeIdCardImageUrl() {
        return negativeIdCardImageUrl;
    }

    public void setNegativeIdCardImageUrl(String negativeIdCardImageUrl) {
        this.negativeIdCardImageUrl = negativeIdCardImageUrl;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

}
