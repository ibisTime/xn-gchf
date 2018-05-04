package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 分页查雇佣员工
 * @author: nyc 
 * @since: 2018年4月29日 下午7:32:21 
 * @history:
 */
public class XN631467Req {

    // （必填）编号
    @NotBlank(message = "编号不能为空")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
