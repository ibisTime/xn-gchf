package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情查合同
 * @author: nyc 
 * @since: 2018年4月29日 下午10:21:06 
 * @history:
 */
public class XN631407Req {

    // （选必） 编号
    @NotBlank(message = "编号不能为空")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
