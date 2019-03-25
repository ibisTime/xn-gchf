package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631667Req extends AListReq {

    /**   
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = -6556976303761523572L;

    @NotBlank
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
