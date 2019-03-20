package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631666Req {
    // 编号
    @NotBlank
    private String code;

    // id
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
