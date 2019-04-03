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
public enum EGender {

    WOMAN("1", "女"),

    MAN("0", "男");

    public static Map<String, EGender> getGenderMap() {
        Map<String, EGender> map = new HashMap<String, EGender>();
        for (EGender type : EGender.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static EGender getGender(String code) {
        Map<String, EGender> map = getGenderMap();
        EGender projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应性别类型不存在");
        }
        return projectCorpType;
    }

    public static void checkExists(String code) {
        Map<String, EGender> map = getGenderMap();
        EGender projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应性别类型不存在");
        }
    }

    public static String checkDictValue(String value) {
        Map<String, EGender> genderMap = getGenderMap();
        Set<Entry<String, EGender>> entrySet = genderMap.entrySet();
        for (Entry<String, EGender> entry : entrySet) {
            if (entry.getValue().getStatus().equals(value)) {
                return entry.getKey();
            }
        }
        throw new BizException("xn0000", value + "对应性别类型不存在");
    }

    EGender(String code, String status) {
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
