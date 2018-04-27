package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631020Req {

    @NotBlank(message = "公司名称不能为空")
    private String name; // 公司名称

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
