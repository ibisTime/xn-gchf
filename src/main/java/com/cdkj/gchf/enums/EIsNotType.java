package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.cdkj.gchf.exception.BizException;

/**
 * 是否类型
 * @author: silver 
 * @since: Mar 29, 2019 2:11:09 PM 
 * @history:
 */
public enum EIsNotType {

    IS("1", "是"),

    NOT("0", "否");

    public static Map<String, EIsNotType> getIsNotTypeMap() {
        Map<String, EIsNotType> map = new HashMap<String, EIsNotType>();
        for (EIsNotType type : EIsNotType.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static EIsNotType getIsNotType(String code) {
        Map<String, EIsNotType> map = getIsNotTypeMap();
        EIsNotType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应是否类型不存在");
        }
        return projectCorpType;
    }

    public static String getIsNotDictCode(String dictValue) {
        Map<String, EIsNotType> map = getIsNotTypeMap();
        Set<Entry<String, EIsNotType>> entrySet = map.entrySet();
        for (Entry<String, EIsNotType> entry : entrySet) {
            if (entry.getValue().getStatus().equals(dictValue)) {
                return entry.getKey();
            }
        }
        throw new BizException("xn0000", dictValue + "对应是否类型不存在");
    }

    public static void checkExists(String code) {
        Map<String, EIsNotType> map = getIsNotTypeMap();
        EIsNotType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应是否类型不存在");
        }
    }

    EIsNotType(String code, String status) {
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
