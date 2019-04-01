package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.gchf.exception.BizException;

/**
 * 进退场类型
 * @author: silver 
 * @since: Mar 29, 2019 2:11:09 PM 
 * @history:
 */
public enum EEntryExitType {

    IN("1", "进场"),

    OUT("0", "退场");

    public static Map<String, EEntryExitType> getDirectionTypeMap() {
        Map<String, EEntryExitType> map = new HashMap<String, EEntryExitType>();
        for (EEntryExitType type : EEntryExitType.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static EEntryExitType getDirectionType(String code) {
        Map<String, EEntryExitType> map = getDirectionTypeMap();
        EEntryExitType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应是否类型不存在");
        }
        return projectCorpType;
    }

    public static void checkExists(String code) {
        Map<String, EEntryExitType> map = getDirectionTypeMap();
        EEntryExitType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应是否类型不存在");
        }
    }

    EEntryExitType(String code, String status) {
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
