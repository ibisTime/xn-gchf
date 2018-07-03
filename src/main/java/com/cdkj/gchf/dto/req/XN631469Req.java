package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详细查询请假明细
 * @author: silver 
 * @since: 2018年7月3日 下午4:34:29 
 * @history:
 */
public class XN631469Req {

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
