package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631815Req extends APageReq {

    /**   
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = 2983636164275176497L;

    // userId
    @NotBlank
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
