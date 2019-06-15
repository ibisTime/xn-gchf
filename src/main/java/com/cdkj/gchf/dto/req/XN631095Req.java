package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author : old3
 * @since : 2019-06-12 21:47
 */
public class XN631095Req {

    // 业务编号 （必填）
    @NotBlank(message = "业务编号不能为空")
    private String bizType;

    // 手机号 （必填）
    @NotBlank(message = "手机号不能为空")
    private String mobile;

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}

    
    