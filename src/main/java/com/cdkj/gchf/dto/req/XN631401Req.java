package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 删除雇佣合同
 * @author: silver 
 * @since: 2018年8月3日 上午10:02:15 
 * @history:
 */
public class XN631401Req {
    // （必填）合同编号
    @NotBlank(message = "合同编号不能为空")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
