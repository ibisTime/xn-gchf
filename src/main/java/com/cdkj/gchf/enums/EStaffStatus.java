package com.cdkj.gchf.enums;

public enum EStaffStatus {
    Work("0", "在职"), Hoilday("1", "请假"), Leave("2", "离职"), NOT_Leave("0,1",
            "未离职");

    EStaffStatus(String code, String value) {
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
