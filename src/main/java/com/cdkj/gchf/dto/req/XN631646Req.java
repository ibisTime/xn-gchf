package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @ClassName:  XN631646Req   
 * @Description:TODO
 * @author: Old3
 * @date:   2019年3月19日 下午10:10:55     
 * @Copyright:
 */
public class XN631646Req {
    // 用户id
    @NotBlank
    private String userId;

    @NotBlank
    private String code;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
