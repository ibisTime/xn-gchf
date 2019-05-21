package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author old3
 * @title: XN631796Req
 * @description: TODO
 * @date 2019-05-20 9:48
 */
public class XN631796Req {


    /**
     * handIdCardImageUrl : 手持身份证url
     * code :  编号
     */
    @NotBlank
    private String handIdCardImageUrl;
    @NotBlank
    private String code;

    public String getHandIdCardImageUrl() {
        return handIdCardImageUrl;
    }

    public void setHandIdCardImageUrl(String handIdCardImageUrl) {
        this.handIdCardImageUrl = handIdCardImageUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
