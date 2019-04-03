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

    public static Map<String, EBankCardStatus> getBankCardStatusMap() {
        Map<String, EBankCardStatus> map = new HashMap<String, EBankCardStatus>();
        for (EBankCardStatus type : EBankCardStatus.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static EBankCardStatus getBankCard(String code) {
        Map<String, EBankCardStatus> map = getBankCardStatusMap();
        EBankCardStatus projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应银行卡状态不存在");
        }
        return projectCorpType;
    }

    public static void checkExists(String code) {
        Map<String, EBankCardStatus> map = getBankCardStatusMap();
        EBankCardStatus projectCorpType = map.get(code);
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
