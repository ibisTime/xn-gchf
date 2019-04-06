package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.gchf.exception.BizException;

/**
 * 考勤方向类型
 * @author: silver 
 * @since: Mar 29, 2019 2:11:09 PM 
 * @history:
 */
public enum EDirectionType {

    IN("01", "进场"),

    OUT("02", "退场");

    public static Map<String, EDirectionType> getDirectionTypeMap() {
        Map<String, EDirectionType> map = new HashMap<String, EDirectionType>();
        for (EDirectionType type : EDirectionType.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static EDirectionType getDirectionType(String code) {
        Map<String, EDirectionType> map = getDirectionTypeMap();
        EDirectionType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应进退场类型不存在");
        }
        return projectCorpType;
    }

    public static void checkExists(String code) {
        Map<String, EDirectionType> map = getDirectionTypeMap();
        EDirectionType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应进退场类型不存在");
        }
    }

    EDirectionType(String code, String status) {
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
