package com.cdkj.gchf.enums;

public enum EProjectStatus {

    To_Edit("0", "待编辑"), To_Building("1", "待开工"), Building("2", "在建"), Stop("3",
            "停工"), End("4", "结束");
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
