package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;

public enum EAttendanceStatus {

    TO_Start("0", "待上班打卡"), Later("1", "迟到"), TO_End("1", "待下班打卡"), EarLy("2",
            "早退"), All("3",
                    "迟到和早退"), Paied("3", "已结算"), Not_Normal("not_n", "非正常");

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
