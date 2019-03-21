package com.cdkj.gchf.domain;

import org.hibernate.validator.constraints.NotBlank;

public class XN631671Req {
    @NotBlank
    private String code;

    @NotBlank
    private String userId;

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

}
