package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631687Req extends AListReq {
    /**   
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = 4649661256910558174L;

    @NotBlank
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
