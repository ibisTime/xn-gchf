package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author: xieyj 
 * @since: 2016年9月17日 下午3:58:52 
 * @history:
 */
public class XN630051Req {

    // 登录名（必填）
    @NotBlank(message = "登录名不能为空")
    private String loginName;

    // 登录密码（选填）
    @NotBlank(message = "登录密码不能为空")
    private String loginPwd;

    // 手机号（必填）
    @NotBlank(message = "手机号不能为空")
    private String mobile;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
