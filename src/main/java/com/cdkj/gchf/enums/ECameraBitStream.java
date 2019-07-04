package com.cdkj.gchf.enums;

import com.cdkj.gchf.exception.BizException;

import java.util.HashMap;
import java.util.Map;

/**
 * 是否类型
 *
 * @author: silver
 * @since: Mar 29, 2019 2:11:09 PM
 * @history:
 */
public enum ECameraBitStream {
    /**
     * 1:主码流 2：副码流
     */
    MAIN("1", "主码流"),

    SECOND("2", "副码流");

    public static Map<String, ECameraBitStream> getIsNotTypeMap() {
        Map<String, ECameraBitStream> map = new HashMap<String, ECameraBitStream>();
        for (ECameraBitStream type : ECameraBitStream.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static void checkExists(String code) {
        Map<String, ECameraBitStream> map = getIsNotTypeMap();
        ECameraBitStream projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应码流不存在");
        }
    }

    ECameraBitStream(String code, String status) {
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
