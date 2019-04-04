package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.gchf.exception.BizException;

/**
 * 计量单位类型
 * @author: silver 
 * @since: Mar 29, 2019 2:11:09 PM 
 * @history:
 */
public enum EUnitType {

    METER("80", "米"),

    SQUAREMETER("81", "平方米"),

    CUBICMETER("82", "立方米");

    public static Map<String, EUnitType> getUnitTypeMap() {
        Map<String, EUnitType> map = new HashMap<String, EUnitType>();
        for (EUnitType type : EUnitType.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static EUnitType getUnitType(String code) {
        Map<String, EUnitType> map = getUnitTypeMap();
        EUnitType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应计量单位类型不存在");
        }
        return projectCorpType;
    }

    public static void checkExists(String code) {
        Map<String, EUnitType> map = getUnitTypeMap();
        EUnitType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应计量单位类型不存在");
        }
    }

    EUnitType(String code, String status) {
        this.code = code;
        this.status = status;
    }

    private String code;

    private String status;

    public String getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

}
