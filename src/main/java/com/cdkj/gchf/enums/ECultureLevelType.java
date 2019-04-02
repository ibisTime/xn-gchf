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
public enum ECultureLevelType {

    PRIMARY("01", "小学"),

    MIDDLE("02", "初中"),

    HIGHT("03", "高中"),

    TECHNICAL("04", "中专"),

    JUNIOR("05", "大专"),

    UNDERGRADUATE("06", "本科"),

    MASTER("07", "硕士"),

    LEARNED("08", "博士"),

    OTHER("99", "其他");

    public static Map<String, ECultureLevelType> getCultureLevelMap() {
        Map<String, ECultureLevelType> map = new HashMap<String, ECultureLevelType>();
        for (ECultureLevelType type : ECultureLevelType.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static ECultureLevelType getCultureType(String code) {
        Map<String, ECultureLevelType> map = getCultureLevelMap();
        ECultureLevelType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应文化程度类型不存在");
        }
        return projectCorpType;
    }

    public static void checkExists(String code) {
        Map<String, ECultureLevelType> map = getCultureLevelMap();
        ECultureLevelType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应文化程度类型不存在");
        }
    }

    ECultureLevelType(String code, String status) {
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
