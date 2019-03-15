package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情查
 * @author: nyc 
 * @since: 2018年4月29日 下午10:19:05 
 * @history:
 */
public class XN631387Req {

    // 编号（必填）
    @NotBlank
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
