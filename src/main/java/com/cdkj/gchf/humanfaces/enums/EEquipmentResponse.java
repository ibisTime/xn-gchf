package com.cdkj.gchf.humanfaces.enums;

import java.util.HashMap;
import java.util.Map;

public enum EEquipmentResponse {
    SHOUQUAN("GS_EXP-100", "接口授权 appKey 错误"),

    SHOUQUANCUOWU("GS_EXP-101", "接口授权 sign 错误"),

    SHEBEIXULIEHAOKONG("GS_EXP-305", "设备创建 deviceKey为空"),

    TIANJIACHENGGONG("GS_SUS300", "添加成功"),

    XULIEHAOZHANYONG("GS_EXP-321", "设备已被被占用,如要添加,请重置"),

    GENGXINCHENGGONG("GS_SUS332", "更新成功"),

    TONGBUCHENGGONG("GS_SUS318", "同步成功");

    EEquipmentResponse(String code, String message) {
        this.code = code;
        this.msg = message;
    }

    private String code;

    private String msg;

    public static Map<String, EEquipmentResponse> getMap() {
        Map<String, EEquipmentResponse> map = new HashMap<>();
        for (EEquipmentResponse item : EEquipmentResponse.values()) {
            map.put(item.getCode(), item);
        }
        return map;
    }

    public static EEquipmentResponse getMessage(String code) {
        Map<String, EEquipmentResponse> map = getMap();
        EEquipmentResponse eEquipmentResponse = map.get(code);

        if (eEquipmentResponse != null) {
            return eEquipmentResponse;
        }
        return null;

    }

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
