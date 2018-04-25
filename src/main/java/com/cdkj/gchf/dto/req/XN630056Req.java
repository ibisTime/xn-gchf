package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author: xieyj 
 * @since: 2016年9月17日 下午3:58:52 
 * @history:
 */
public class XN630056Req {

    // 管理员编号(必填)
    @NotBlank(message = "用户编号不能为空")
    private String adminId;

    // 管理员登录密码(必填)
    @NotBlank(message = "原登录密码不能为空")
    private String adminLoginPwd;

    // 用户编号(必填)
    @NotBlank(message = "用户编号不能为空")
    private String userId;

    // 登录密码(必填)
    @NotBlank(message = "新登录密码不能为空")
    private String loginPwd;

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminLoginPwd() {
        return adminLoginPwd;
    }

    public void setAdminLoginPwd(String adminLoginPwd) {
        this.adminLoginPwd = adminLoginPwd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

}
