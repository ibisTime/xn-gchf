package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.cdkj.gchf.exception.BizException;

/**
 * 参建单位类型
 * @author: silver 
 * @since: Mar 29, 2019 2:11:09 PM 
 * @history:
 */
public enum ECorpType {

    NEIZI("100", "内资企业"),

    GUOYOU("110", "国有企业"),

    JITI("120", "集体企业"),

    GUFENHEZUO("130", "股份合作企业"),

    LIANYING("140", "联营企业"), GUOYOULIANYING("141", "国有联营企业"),

    JIYILIANYING("142", "集体联营企业"),

    GUOYOUYUJITI("143", "国有与集体联营企业"),

    QITALIANYING("149", "其他联营企业"),

    YOUXIANZEREN("150", "有限责任公司"),

    GUOYOUDUZI("151", "国有独资公司"),

    QITAYOUXIANZEREN("159", "其他有限责任公司"),

    GUFEN("160", "股份有限公司"),

    SIYING("170", "私营企业"),

    SIYINGDUZI("171", "私营独资企业"),

    SIYINGHEZUO("172", "私营合伙企业"),

    SIYINGYOUXIANZEREN("173", "私营有限责任公司"),

    SIYINGGUFENYOUXIAN("174", "私营股份有限公司"),

    QITAQIYE("190", "其他企业"),

    GANGAOTAISHANGTOUZI("200", "港、澳、台商投资企业"),

    HEZIJINGYINGQIYE("210", "合资经营企业（港或澳、台资）"),

    HEZUOJINGYINGQIYE("220", "合作经营企业（港或澳、台资）"),

    GANGAOTAIDUZIJINGYING("230", "港、澳、台商独资经营企业"),

    GANGAOTAISHANGTOUZIGUFEN("240", "港、澳、台商投资股份有限公司"),

    QITAGANGAOTAISHANGTOUZI("290", "其他港、澳、台商投资企业"),

    WAISHANGTOUZI("300", "外商投资企业"),

    ZHONGWAIHEZIJINGYING("310", "中外合资经营企业"),

    ZHONGWAIHEZUOJINGYING("320", "中外合作经营企业"),

    WAIZI("330", "外资企业"),

    WAISHANGTOUZIGUFEN("340", "外商投资股份有限公司"),

    QITAWAISHANGTOUZI("390", "其他外商投资企业"),

    JUNDUI("810", "军队单位");

    public static Map<String, ECorpType> getProjectCorpTypeMap() {
        Map<String, ECorpType> map = new HashMap<String, ECorpType>();
        for (ECorpType type : ECorpType.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static ECorpType getProjectCorpType(String code) {
        Map<String, ECorpType> map = getProjectCorpTypeMap();
        ECorpType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应企业注册类型不存在");
        }
        return projectCorpType;
    }

    public static void checkExists(String code) {
        Map<String, ECorpType> map = getProjectCorpTypeMap();
        ECorpType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应企业注册类型不存在");
        }
    }

    public static String getProjectCorpDictValue(String dictValue) {
        Map<String, ECorpType> projectCorpTypeMap = getProjectCorpTypeMap();
        Set<Entry<String, ECorpType>> entrySet = projectCorpTypeMap.entrySet();
        for (Entry<String, ECorpType> entry : entrySet) {
            if (entry.getValue().getStatus().equals(dictValue)) {
                return entry.getKey();
            }
        }
        throw new BizException("xn0000", dictValue + "对应企业注册类型不存在");
    }

    ECorpType(String code, String status) {
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
