package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631667Req extends AListReq {

    @NotBlank
    private String userId;
}
