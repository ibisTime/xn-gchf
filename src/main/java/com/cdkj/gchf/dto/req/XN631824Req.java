package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author : old3
 * @since : 2019-05-30 16:08
 */
public class XN631824Req {

    @NotBlank
    private String deviceKey;
    @NotBlank
    private String code;

    public String getDeviceKey() {
        return deviceKey;
    }

    public void setDeviceKey(String deviceKey) {
        this.deviceKey = deviceKey;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

    
    