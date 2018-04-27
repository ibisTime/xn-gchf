package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631357Req {
    private static final long serialVersionUID = 5138736221155343722L;

    @NotBlank
    private String code;// 编号

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
