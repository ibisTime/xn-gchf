package com.cdkj.coin.enums;

public enum EEthAddressType {
    W("W", "归集地址"), M("M", "散取地址"), X("X", "分发地址"), Y("Y", "提现地址");

    EEthAddressType(String code, String value) {
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
