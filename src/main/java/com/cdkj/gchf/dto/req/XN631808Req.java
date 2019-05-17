package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631808Req extends AListReq {

    private static final long serialVersionUID = -2285628857192004496L;

    @NotBlank
    private String idCardNumber;

    // private String userId;

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    // public String getUserId() {
    // return userId;
    // }
    //
    // public void setUserId(String userId) {
    // this.userId = userId;
    // }

}
