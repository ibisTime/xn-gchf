package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 删除操作指南
 * @author: silver 
 * @since: 2018年8月6日 下午5:07:09 
 * @history:
 */
public class XN631121Req {

    // 编号
    @NotBlank(message = "编号不能为空")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
