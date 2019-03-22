package com.cdkj.gchf.gov;

public class QueueBean {

    /**
     * 国家平台接口异步调用结果
     */
    private String connectRes;

    /**
     * 项目编号
     */
    private String projectCode;

    /**
     * 项目秘钥
     */
    private String secret;

    /**
     * bo类名
     */
    private String boClass;

    /**
     * 本地数据编号
     */
    private String code;

    /**
     * 上传状态
     */
    private String status;

    public String getConnectRes() {
        return connectRes;
    }

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

    public void setConnectRes(String connectRes) {
        this.connectRes = connectRes;
    }

    public String getBoClass() {
        return boClass;
    }

    public void setBoClass(String boClass) {
        this.boClass = boClass;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public QueueBean(String connectRes, String projectCode, String secret,
            String boClass, String code, String status) {
        super();
        this.connectRes = connectRes;
        this.projectCode = projectCode;
        this.secret = secret;
        this.boClass = boClass;
        this.code = code;
        this.status = status;
    }

}
