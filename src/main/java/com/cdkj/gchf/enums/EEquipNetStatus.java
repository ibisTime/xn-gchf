package com.cdkj.gchf.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 设备网络状态
 *
 * @author: silver
 * @since: May 10, 2019 2:09:03 PM
 * @history:
 */
public enum EEquipNetStatus {

    ONLINE("1", "在线"),

    OFFLINE("2", "离线");

    public static String parseValue(String key) {

        if (StringUtils.isBlank(key)) {
            return null;
        }

        String value = key;
        for (EEquipNetStatus equipResultMsg : EEquipNetStatus.values()) {
            if (key.contains(equipResultMsg.value)) {
                value = equipResultMsg.value;
            }
        }

        return value;
    }

    EEquipNetStatus(String code, String value) {
        this.code = code;
        this.value = value;
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
