package com.cdkj.gchf.enums;

/**
 * @author old3
 */

public enum EDeleteStatus {
    //删除状态
    DELETED("1", "已删除"),

    NORMAL("0", "正常");

    EDeleteStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
