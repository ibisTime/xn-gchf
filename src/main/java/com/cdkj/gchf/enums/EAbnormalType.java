package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;

public enum EAbnormalType {

    Salary_Abnormal("0", "工资条异常");

    public static Map<String, EAbnormalType> getEAbnormalTypeMap() {
        Map<String, EAbnormalType> map = new HashMap<String, EAbnormalType>();
        for (EAbnormalType type : EAbnormalType.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static EAbnormalType getEAbnormalType(String code) {
        Map<String, EAbnormalType> map = getEAbnormalTypeMap();
        EAbnormalType result = map.get(code);
        return result;
    }

    EAbnormalType(String code, String value) {
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

    public void setCode(String code) {
        this.code = code;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
