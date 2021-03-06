package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 删除企业信息
 * @author: silver 
 * @since: Mar 13, 2019 1:34:26 PM 
 * @history:
 */
public class XN631252Req {

    // 编号
    @NotBlank
    private String code;

    // 用户编号
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
