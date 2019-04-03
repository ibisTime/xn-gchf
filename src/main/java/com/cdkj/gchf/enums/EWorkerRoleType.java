package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.cdkj.gchf.exception.BizException;

/**
 * 工人类型
 * @author: silver 
 * @since: Mar 29, 2019 2:11:09 PM 
 * @history:
 */
public enum EWorkerRoleType {

    GUANLI("10", "管理人员"),

    JIANZHUGONGREN("20", "建筑工人");

    public static Map<String, EWorkerRoleType> getWorkerRoleTypeMap() {
        Map<String, EWorkerRoleType> map = new HashMap<String, EWorkerRoleType>();
        for (EWorkerRoleType type : EWorkerRoleType.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static EWorkerRoleType getWorkerRoleType(String code) {
        Map<String, EWorkerRoleType> map = getWorkerRoleTypeMap();
        EWorkerRoleType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应是否类型不存在");
        }
        return projectCorpType;
    }

    public static void checkExists(String code) {
        Map<String, EWorkerRoleType> map = getWorkerRoleTypeMap();
        EWorkerRoleType projectCorpType = map.get(code);
        if (null == projectCorpType) {
            throw new BizException("xn0000", code + "对应是否类型不存在");
        }
    }

    public static String getWorkerRoleCode(String code) {
        Map<String, EWorkerRoleType> map = getWorkerRoleTypeMap();
        Set<Entry<String, EWorkerRoleType>> entrySet = map.entrySet();
        for (Entry<String, EWorkerRoleType> entry : entrySet) {
            if (entry.getValue().getStatus().equals(code)) {
                return entry.getKey();
            }
        }
        throw new BizException("xn0000", code + "对应工人类型不存在");
    }

    EWorkerRoleType(String code, String status) {
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
