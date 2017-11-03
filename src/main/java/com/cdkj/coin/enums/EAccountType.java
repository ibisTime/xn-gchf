package com.cdkj.coin.enums;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.coin.exception.BizException;

/**
 * @author: xieyj 
 * @since: 2016年11月11日 上午10:54:16 
 * @history:
 */
public enum EAccountType {
    Customer("C", "C端账号"), Merchant("B", "B端账号"), Plat("P", "平台账号");

    public static Map<String, EAccountType> getAccountTypeResultMap() {
        Map<String, EAccountType> map = new HashMap<String, EAccountType>();
        for (EAccountType type : EAccountType.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static EAccountType getAccountType(String code) {
        Map<String, EAccountType> map = getAccountTypeResultMap();
        EAccountType result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的ccountType不存在");
        }
        return result;
    }

    EAccountType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

}
