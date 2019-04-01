package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631816Req extends AListReq {

    /**   
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = -6764619204970061185L;

    @NotBlank
    private String userId;

}
