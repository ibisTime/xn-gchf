package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author : old3
 * @since : 2019-06-21 2:04
 */
public class XN631854Req {

    @NotBlank(message = "用户id不能为空")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

    
    