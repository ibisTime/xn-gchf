package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.cdkj.gchf.exception.BizException;

/**
 * 身份证件类型
 * @author: silver 
 * @since: Mar 29, 2019 2:11:09 PM 
 * @history:
 */
public enum EIdCardType {

    JUMIN("01", "居民身份证"),

    JUNGUAN("02", "军官证"),

    WUJINGWUGUAN("03", "武警警官证"),

    SHIBING("04", "士兵证"),

    JUNDUITUIXIU("05", "军队离退休干部证"),

    CANJI("06", "残疾人证"),

    CANJIJUNREN("07", "残疾军人证（1-8 级）"),

    HUZHAO("08", "护照"),

    GANGAOTONGBAOHUIXIANG("09", "港澳同胞回乡证"),

    GANGAOJUMINLAIWANGNEIDI("10", "港澳居民来往内地通行证"),

    WANGLAIGANGAOTONGXINGZHE("11", "中华人民共和国往来港澳通行证"),

    TAIWANJUMIN("12", "台湾居民来往大陆通行证"),

    DALUJUMINWANGLAI("13", "大陆居民往来台湾通行证"),

    WAIJIAO("14", "外交官证"),

    LINGSHIGUAN("15", "领事馆证"),

    HAIYUAN("16", "海员证"),

    XIANGGANG("17", "香港身份证");

    public static Map<String, EIdCardType> getIdCardTypeMap() {
        Map<String, EIdCardType> map = new HashMap<String, EIdCardType>();
        for (EIdCardType type : EIdCardType.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static EIdCardType getIdCardType(String code) {
        Map<String, EIdCardType> map = getIdCardTypeMap();
        EIdCardType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应证件类型不存在");
        }
        return projectCorpType;
    }

    public static String getIdCardDictValue(String dictValue) {
        Map<String, EIdCardType> map = getIdCardTypeMap();
        Set<Entry<String, EIdCardType>> entrySet = map.entrySet();
        for (Entry<String, EIdCardType> entry : entrySet) {
            if (entry.getValue().getStatus().equals(dictValue)) {
                return entry.getKey();
            }
        }
        throw new BizException("xn0000", dictValue + "对应证件类型不存在");
    }

    public static void checkExists(String code) {
        Map<String, EIdCardType> map = getIdCardTypeMap();
        EIdCardType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应证件类型不存在");
        }
    }

    public static String checkDictValue(String dictValue) {
        Map<String, EIdCardType> idCardTypeMap = getIdCardTypeMap();
        Set<Entry<String, EIdCardType>> entrySet = idCardTypeMap.entrySet();
        for (Entry<String, EIdCardType> entry : entrySet) {
            if (entry.getValue().getStatus().equals(dictValue)) {
                return entry.getKey();
            }
        }
        throw new BizException("XN000000", dictValue + "对应证件类型不存在");
    }

    EIdCardType(String code, String status) {
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
