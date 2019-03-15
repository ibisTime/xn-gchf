package com.cdkj.gchf.enums;

public enum ESalaryLogType {

    Normal("0", "正常"), Abnormal("1", "异常");

    ESalaryLogType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
