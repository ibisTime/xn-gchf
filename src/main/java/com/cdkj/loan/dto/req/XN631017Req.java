package com.cdkj.loan.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author: xieyj 
 * @since: 2016年9月17日 下午3:58:52 
 * @history:
 */
public class XN631017Req {
    // 配置类型（必填）
    @NotBlank(message = "配置类型不能为空")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
