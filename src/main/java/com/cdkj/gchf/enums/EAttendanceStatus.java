package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;

public enum EAttendanceStatus {

    Normal("0", "正常"), Later("1", "迟到"), EarLy("2", "早退"), All("3",
            "迟到和早退"), Not_Normal("4", "非正常");

    public static Map<String, EAccountType> getAccountTypeResultMap() {
        Map<String, EAccountType> map = new HashMap<String, EAccountType>();
        for (EAccountType type : EAccountType.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    EAttendanceStatus(String code, String value) {
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
