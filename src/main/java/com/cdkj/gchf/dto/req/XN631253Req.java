package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 上传企业信息
 * @author: silver 
 * @since: Mar 13, 2019 1:34:26 PM 
 * @history:
 */
public class XN631253Req {

    // 编号
    @NotBlank
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
