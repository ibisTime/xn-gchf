package com.cdkj.gchf.enums;

/**
 * 异步处理状态
 * @author: silver 
 * @since: Mar 21, 2019 2:01:14 PM 
 * @history:
 */
public enum EGovAsyncStatus {
    TO_HANDLE("0", "待处理"),

    FAIL("10", "处理失败"),

    SUCCESS("20", "处理成功");

    EGovAsyncStatus(String code, String value) {
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
