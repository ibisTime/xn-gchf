package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 入职职位
 * @author: silver 
 * @since: 2018年8月6日 上午11:32:10 
 * @history:
 */
public enum EPositionType {

    Normal("0", "普工"), Manager("1", "主管");

    public static Map<String, EPositionType> getAccountTypeResultMap() {
        Map<String, EPositionType> map = new HashMap<String, EPositionType>();
        for (EPositionType type : EPositionType.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    EPositionType(String code, String value) {
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
