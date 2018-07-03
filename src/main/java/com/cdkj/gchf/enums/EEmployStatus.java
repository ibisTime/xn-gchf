package com.cdkj.gchf.enums;

public enum EEmployStatus {
    Work("0", "在职"), Hoilday("1", "请假"), Leave("2", "离职"), Not_Leave("0,1",
            "未离职");

    EEmployStatus(String code, String value) {
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
