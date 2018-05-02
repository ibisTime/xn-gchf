package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情查项目
 * @author: xieyj 
 * @since: 2016年4月17日 上午8:26:30 
 * @history:
 */
public class XN631358Req {

    @NotBlank
    private String code;// 编号

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
