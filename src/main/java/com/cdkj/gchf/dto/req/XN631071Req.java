package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author: xieyj 
 * @since: 2016年9月17日 下午3:58:52 
 * @history:
 */
public class XN631071Req {

    // 登录名（必填）
    @NotBlank(message = "登录名不能为空")
    private String loginName;

    // 登录密码（必填）
    @NotBlank(message = "登录密码不能为空")
    private String loginPwd;

    // 类型（必填）
    @NotBlank(message = "类型不能为空")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

}
