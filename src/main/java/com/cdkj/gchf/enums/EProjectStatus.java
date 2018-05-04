package com.cdkj.gchf.enums;

public enum EProjectStatus {

    TO_AUDIT_NO("0", "待提请待审核"), TO_AUDIT_YES("1", "待审核"), PASS("2",
            "通过"), Building("3", "在建"), Stop("4", "停工"), End("5", "项目结束");
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
