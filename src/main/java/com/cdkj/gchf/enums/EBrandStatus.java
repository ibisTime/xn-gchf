package com.cdkj.gchf.enums;

public enum EBrandStatus {

    TO_UP("0", "待上架"), UP("1", "已上架"), DOWN("2", "已下架");
    EBrandStatus(String code, String status) {
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
