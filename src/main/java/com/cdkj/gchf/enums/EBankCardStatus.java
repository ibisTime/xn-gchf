package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.gchf.exception.BizException;

public enum EBankCardStatus {

    Normal("0", "正常"), Freeze("1", "冻结");

    EBankCardStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EContractPeriodType> getBankCardStatusMap() {
        Map<String, EContractPeriodType> map = new HashMap<String, EContractPeriodType>();
        for (EContractPeriodType type : EContractPeriodType.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static EContractPeriodType getBankCard(String code) {
        Map<String, EContractPeriodType> map = getBankCardStatusMap();
        EContractPeriodType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应银行卡状态不存在");
        }
        return projectCorpType;
    }

    public static void checkExists(String code) {
        Map<String, EContractPeriodType> map = getBankCardStatusMap();
        EContractPeriodType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应银行卡状态不存在");
        }
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
