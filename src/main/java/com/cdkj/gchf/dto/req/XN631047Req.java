package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 角色-详情
 * @author: Gejin 
 * @since: 2016年4月16日 下午5:24:01 
 * @history:
 */
public class XN631047Req {

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
