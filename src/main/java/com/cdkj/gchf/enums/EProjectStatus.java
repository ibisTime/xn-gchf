package com.cdkj.gchf.enums;

public enum EProjectStatus {

    To_Audit("0", "待提交"), UnApprove("1", "已提交待审核"), UnPass("2",
            "审核不通过"), Building("3", "在建"), Stop("4", "停工"), End("5", "完工");
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
