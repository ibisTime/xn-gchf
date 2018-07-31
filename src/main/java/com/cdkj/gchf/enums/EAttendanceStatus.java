package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;

public enum EAttendanceStatus {

    TO_Start("0", "待上班打卡"), TO_End("1", "待下班打卡"), Unpaied("2",
            "已打卡待结算"), Paied("3", "已结算"), Absent("4", "旷工");

    public static Map<String, EAttendanceStatus> getAccountTypeResultMap() {
        Map<String, EAttendanceStatus> map = new HashMap<String, EAttendanceStatus>();
        for (EAttendanceStatus type : EAttendanceStatus.values()) {
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
