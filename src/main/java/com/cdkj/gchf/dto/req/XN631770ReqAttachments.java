package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631770ReqAttachments {
    @NotBlank
    private String name;

    @NotBlank
    private String data;

}
