package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631605Req extends APageReq {

    /**   
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = -4625031485854003503L;

    @NotBlank
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
