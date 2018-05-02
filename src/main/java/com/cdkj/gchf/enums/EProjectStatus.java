package com.cdkj.gchf.enums;

public enum EProjectStatus {

    TO_AUDIT_NO("0", "待提请待审核"), TO_AUDIT_YES("1", "待审核"), PASS("2",
            "通过"), NOTPASS("4", "项目结束");
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
