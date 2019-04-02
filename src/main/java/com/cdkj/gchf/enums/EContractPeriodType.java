package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.gchf.exception.BizException;

/**
 * 劳动和同类型
 * @author: silver 
 * @since: Mar 29, 2019 2:11:09 PM 
 * @history:
 */
public enum EContractPeriodType {

    QIXIAN("1", "以完成一定工作为期限的合同"),

    GUDING("0", "固定期限合同");

    public static Map<String, EContractPeriodType> getContractPeriodTypeMap() {
        Map<String, EContractPeriodType> map = new HashMap<String, EContractPeriodType>();
        for (EContractPeriodType type : EContractPeriodType.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static EContractPeriodType getContractPeriodType(String code) {
        Map<String, EContractPeriodType> map = getContractPeriodTypeMap();
        EContractPeriodType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应合同类型不存在");
        }
        return projectCorpType;
    }

    public static void checkExists(String code) {
        Map<String, EContractPeriodType> map = getContractPeriodTypeMap();
        EContractPeriodType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应合同类型不存在");
        }
    }

    EContractPeriodType(String code, String status) {
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
