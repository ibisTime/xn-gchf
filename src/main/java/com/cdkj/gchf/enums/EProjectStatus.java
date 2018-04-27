package com.cdkj.gchf.enums;

public enum EProjectStatus {

    TO_AUDIT("0", "待审核"), PASS("1", "通过"), NOTPASS("2", "不通过");
    EProjectStatus(String code, String status) {
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
