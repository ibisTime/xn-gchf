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
public enum EProjectCorpType {

    ZHUANYE("001", "专业分包"),

    SHEBEI("002", "设备分包"),

    CAILIAO("003", "材料分包"),

    HOUQIN("004", "后勤服务"),

    TESHU("005", "特殊设备"),

    LAOWU("006", "劳务分包"),

    JIANLI("007", "监理"),

    JIANSHE("008", "建设单位"),

    ZONGCHENGBAO("009", "总承包单位"),

    KANCHA("010", "勘察"),

    SHEJI("011", "设计单位"),

    QITA("012", "其它");

    public static Map<String, EProjectCorpType> getProjectCorpTypeMap() {
        Map<String, EProjectCorpType> map = new HashMap<String, EProjectCorpType>();
        for (EProjectCorpType type : EProjectCorpType.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static EProjectCorpType getProjectCorpType(String code) {
        Map<String, EProjectCorpType> map = getProjectCorpTypeMap();
        EProjectCorpType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应参建单位类型类型不存在");
        }
        return projectCorpType;
    }

    public static void checkExists(String code) {
        Map<String, EProjectCorpType> map = getProjectCorpTypeMap();
        EProjectCorpType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应参建单位类型类型不存在");
        }
    }

    public static String getProjectCorpDictValue(String dictValue) {
        Map<String, EProjectCorpType> projectCorpTypeMap = getProjectCorpTypeMap();
        Set<Entry<String, EProjectCorpType>> entrySet = projectCorpTypeMap
            .entrySet();
        for (Entry<String, EProjectCorpType> entry : entrySet) {
            if (entry.getValue().getStatus().equals(dictValue)) {
                return entry.getKey();
            }
        }
        throw new BizException("xn0000", dictValue + "对应参建单位类型类型不存在");
    }

    EProjectCorpType(String code, String status) {
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
