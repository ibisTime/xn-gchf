package com.cdkj.gchf.enums;

/**
 * 项目配置状态
 * @author: silver 
 * @since: Mar 25, 2019 3:09:02 PM 
 * @history:
 */
public enum EProjectConfigStatus {

    START("0", "启用"),

    STOP("1", "停用");

    EProjectConfigStatus(String code, String status) {
        this.code = code;
        this.status = status;
    }

    private String code;

    private String status;

    public String getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

}
