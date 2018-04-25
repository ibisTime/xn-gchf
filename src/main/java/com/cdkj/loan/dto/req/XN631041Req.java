package com.cdkj.loan.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 删除角色
 * @author: xieyj 
 * @since: 2016年5月16日 下午10:06:08 
 * @history:
 */
public class XN631041Req {

    // 角色编号(必填)
    @NotBlank(message = "编号不能为空")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
