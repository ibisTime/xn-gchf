package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改项目配置
 * @author: silver 
 * @since: Mar 13, 2019 1:34:10 PM 
 * @history:
 */
public class XN631622Req {

    // 编号
    @NotBlank
    private String localProjectCode;

    // 国家平台项目编码
    @NotBlank
    private String projectCode;

    // 国家平台项目密码
    @NotBlank
    private String password;

    // 项目秘钥
    @NotBlank
    private String secret;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getLocalProjectCode() {
        return localProjectCode;
    }

    public void setLocalProjectCode(String localProjectCode) {
        this.localProjectCode = localProjectCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
