package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotEmpty;

public class XN631631Req {
    @NotEmpty
    String code;

    @NotEmpty
    String userId;

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
