package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 删除技能
 * @author: silver 
 * @since: 2018年7月12日 下午3:34:44 
 * @history:
 */
public class XN631501Req {
    // 技能编号
    @NotBlank
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
