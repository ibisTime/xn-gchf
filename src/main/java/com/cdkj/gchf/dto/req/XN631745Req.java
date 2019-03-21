package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631745Req extends APageReq {
    /**   
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = -7933187523227424529L;

    @NotBlank
    private String userId;

}
