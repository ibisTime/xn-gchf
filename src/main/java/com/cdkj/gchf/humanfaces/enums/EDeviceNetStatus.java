package com.cdkj.gchf.humanfaces.enums;

/**
 * @author old3
 */

public enum EDeviceNetStatus {
    //设备网络在线
    ONLINE("1", "在线"),
    //设备网络离线
    OFFLINE("2", "离线");

    EDeviceNetStatus(String code, String status) {
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
