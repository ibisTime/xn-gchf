package com.cdkj.gchf.humanfaces.enums;

public enum EEquipmentWorkerResponse {
    SHOUQUAN("GS_EXP-100", "接口授权 appKey 错误"),

    SHOUQUANCUOWU("GS_EXP-101", "接口授权 sign 错误"),

    SHOUQUANSHIBAI("GS_EXP-200", "人员录入失败"),

    RENYUANCHAXUNSHIBAI("GS_EXP-201", "人员查询失败"),

    RENYUANBUCUNZAI("GS_EXP-212", "人员不存在"),

    TIANJIACHENGGONG("GS_SUS200", "添加成功"),

    TUPIANLEIXINGCUOWU("GS_EXP-610", "文件不为jpg或png类型"),

    CHAXUNCHENGGONG("GS_SUS202", "查询成功"),

    SHOUQUANCHENGONG("GS_SUS327", "授权成功"),

    TUPIANGUODA("GS_EXP607", "图片过大"),

    BUSHUYU("GS_EXP610", "照片不属于该应用"),

    WUSHOUQUANRENYUAN("-1", "无授权人员"),

    TUPIANCHAOGUOSHULIANG("GS_EXP606", "超出照片添加数量 ");

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
