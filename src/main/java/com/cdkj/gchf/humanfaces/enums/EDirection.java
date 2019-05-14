package com.cdkj.gchf.humanfaces.enums;

/**
 * @author old3
 */

public enum EDirection {
    //设备方向
    IN("1", "入场"),
    //设备方向
    OUT("2", "离场");

    EDirection(String code, String status) {
        this.code = code;
        this.status = status;
    }

    private String code;

    private String status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
