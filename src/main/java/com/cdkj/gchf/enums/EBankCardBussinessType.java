package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.gchf.exception.BizException;

public enum EBankCardBussinessType {

    CORP("001", "参建单位"),

    USER("002", "个人用户");

    EBankCardBussinessType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EBankCardBussinessType> getBankCardBussinessMap() {
        Map<String, EBankCardBussinessType> map = new HashMap<String, EBankCardBussinessType>();
        for (EBankCardBussinessType type : EBankCardBussinessType.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static EBankCardBussinessType getBankCardBussiness(String code) {
        Map<String, EBankCardBussinessType> map = getBankCardBussinessMap();
        EBankCardBussinessType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应业务类型不存在");
        }
        return projectCorpType;
    }

    public static void checkExists(String code) {
        Map<String, EBankCardBussinessType> map = getBankCardBussinessMap();
        EBankCardBussinessType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应业务类型不存在");
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
