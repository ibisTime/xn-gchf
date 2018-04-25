package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author: xieyj 
 * @since: 2016年9月17日 下午3:58:52 
 * @history:
 */
public class XN630054Req {

    // 手机号(必填)
    @NotBlank(message = "手机号不能为空")
    private String mobile;

    // 手机验证码(必填)
    @NotBlank(message = "手机验证不能为空")
    private String smsCaptcha;

    // 新登录密码(必填)
    @NotBlank(message = "新登录密码不能为空")
    private String newLoginPwd;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNewLoginPwd() {
        return newLoginPwd;
    }

    public void setNewLoginPwd(String newLoginPwd) {
        this.newLoginPwd = newLoginPwd;
    }

    public String getSmsCaptcha() {
        return smsCaptcha;
    }

    public void setSmsCaptcha(String smsCaptcha) {
        this.smsCaptcha = smsCaptcha;
    }

}
