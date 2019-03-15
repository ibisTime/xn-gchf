package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;

public enum EStaffType {
    ZH_Staff("0", "直招工人"), LW_Staff("1", "劳务工人"), BG_Staff("2",
            "包工工人"), NQ_Staff("3", "内勤工人");

    public static Map<String, EStaffType> getMap() {
        Map<String, EStaffType> map = new HashMap<String, EStaffType>();
        for (EStaffType staffType : EStaffType.values()) {
            map.put(staffType.getCode(), staffType);
        }
        return map;
    }

    EStaffType(String code, String value) {
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
