package com.cdkj.gchf.humanfaces;

public enum EResponseCode {
    SHOUQUAN("GS_EXP-100", "接口授权 appKey 错误"),

    SHOUQUANCUOWU("GS_EXP-101", "接口授权 sign 错误"),

    SHOUQUANSHIBAI("GS_EXP-200", "人员录入失败"),

    RENYUANCHAXUNSHIBAI("GS_EXP-201", "人员查询失败"),

    RENYUANBUCUNZAI("GS_EXP-212", "人员不存在"),

    SHEBEIXULIEHAOKONG("GS_EXP-305", "设备创建 deviceKey为空");

    EResponseCode(String code, String message) {
        this.code = code;
        this.msg = message;
    }

    private String code;

    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
