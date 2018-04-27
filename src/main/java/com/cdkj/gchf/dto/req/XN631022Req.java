package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631022Req {

    @NotBlank(message = "编号不能为空")
    private String code;// 编号

    @NotBlank(message = "公司名称不能为空")
    private String name;// 公司名称

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
