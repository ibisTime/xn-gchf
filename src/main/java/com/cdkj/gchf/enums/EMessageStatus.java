package com.cdkj.gchf.enums;

public enum EMessageStatus {

    TO_Send("0", "待发送"), TO_Deal("1", "未处理"), TO_Feedback("2",
            "待反馈"), Approved("3", "已处理");

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
