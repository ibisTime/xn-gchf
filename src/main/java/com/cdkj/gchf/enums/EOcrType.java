package com.cdkj.gchf.enums;

/**
 * @author old3
 */

public enum EOcrType {
    /**
     * 0：客户端传信息获取信息 1：确认后录入信息
     */
    GET("0", "获取信息"),

    INPUT("1", "录入信息");

    EOcrType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
