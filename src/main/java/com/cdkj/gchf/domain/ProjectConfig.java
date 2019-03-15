package com.cdkj.gchf.domain;

import com.cdkj.gchf.dao.base.ABaseDO;

/**
* 项目配置
* @author: xiongk 
* @since: 2019-03-15 10:54:48
* @history:
*/
public class ProjectConfig extends ABaseDO {

    private static final long serialVersionUID = -660563721744879227L;

    // 编号
    private String code;

    // 本地项目编号
    private String localProjectCode;

    // 国家平台项目编码
    private String projectCode;

    // 项目秘钥
    private String secret;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setLocalProjectCode(String localProjectCode) {
        this.localProjectCode = localProjectCode;
    }

    public String getLocalProjectCode() {
        return localProjectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getSecret() {
        return secret;
    }

}
