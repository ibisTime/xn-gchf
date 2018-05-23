package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情查询技能
 * @author: nyc 
 * @since: 2018年5月23日 下午4:57:36 
 * @history:
 */
public class XN631507Req {

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
