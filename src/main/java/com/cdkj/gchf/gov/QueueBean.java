package com.cdkj.gchf.gov;

public class QueueBean {

    /**
     * 国家平台接口异步调用结果
     */
    private String requestSerialCode;

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

    /**
     * 日志编号
     */
    private String logCode;

    /**
     * 用户id
     */
    private String userId;

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

    public String getRequestSerialCode() {
        return requestSerialCode;
    }

    public void setRequestSerialCode(String requestSerialCode) {
        this.requestSerialCode = requestSerialCode;
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

    public String getLogCode() {
        return logCode;
    }

    public void setLogCode(String logCode) {
        this.logCode = logCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public QueueBean(String requestSerialCode, String projectCode,
                     String secret, String boClass, String code, String status,
                     String logCode, String userId) {
        super();
        this.requestSerialCode = requestSerialCode;
        this.projectCode = projectCode;
        this.secret = secret;
        this.boClass = boClass;
        this.code = code;
        this.status = status;
        this.logCode = logCode;
        this.userId = userId;
    }
}
