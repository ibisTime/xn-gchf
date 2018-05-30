package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 校验登录名
 * @author: nyc 
 * @since: 2018年5月30日 上午11:28:10 
 * @history:
 */
public class XN631079Req {

    // 登录名
    @NotBlank(message = "录名不能为空")
    private String loginName;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

}
