package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author: xieyj 
 * @since: 2016年9月17日 下午3:58:52 
 * @history:
 */
public class XN631070Req {

    // 用户类型（必填）
    @NotBlank(message = "用户类型不能为空")
    private String type;

    // 手机号（必填）
    @NotBlank(message = "手机号不能为空")
    private String mobile;

    // 登录密码（必填）
    @NotBlank(message = "登录密码不能为空")
    private String loginPwd;

    // 推荐人（选填）
    private String userRefree;

    // 备注（选填）
    private String remark;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserRefree() {
        return userRefree;
    }

    public void setUserRefree(String userRefree) {
        this.userRefree = userRefree;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
