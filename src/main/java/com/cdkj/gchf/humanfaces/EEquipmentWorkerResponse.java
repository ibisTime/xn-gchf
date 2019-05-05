package com.cdkj.gchf.humanfaces;

public enum EEquipmentWorkerResponse {
    SHOUQUAN("GS_EXP-100", "接口授权 appKey 错误"),

    SHOUQUANCUOWU("GS_EXP-101", "接口授权 sign 错误"),

    SHOUQUANSHIBAI("GS_EXP-200", "人员录入失败"),

    RENYUANCHAXUNSHIBAI("GS_EXP-201", "人员查询失败"),

    RENYUANBUCUNZAI("GS_EXP-212", "人员不存在"),

    TIANJIACHENGGONG("GS_SUS200", "添加成功"),

    TUPIANLEIXINGCUOWU("GS_EXP-610", "文件不为jpg或png类型"),

    SHOUQUANCHENGONG("GS_SUS327", "授权成功");

    EEquipmentWorkerResponse(String code, String message) {
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