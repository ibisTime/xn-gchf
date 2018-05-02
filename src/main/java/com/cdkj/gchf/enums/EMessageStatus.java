package com.cdkj.gchf.enums;

public enum EMessageStatus {

    Send_NO("0", "待发送"), Send_YES("1", "待处理"), Approved("2", "已处理");

    EMessageStatus(String code, String value) {
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
