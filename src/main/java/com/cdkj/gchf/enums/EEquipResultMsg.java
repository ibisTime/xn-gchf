package com.cdkj.gchf.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 考勤来源
 *
 * @author: silver
 * @since: May 7, 2019 8:30:15 PM
 * @history:
 */
public enum EEquipResultMsg {

    device_state_incorrect("device state incorrect", "设备未处于可操作状态");

    public static String parseValue(String key) {

        if (StringUtils.isBlank(key)) {
            return null;
        }

        String value = key;
        for (EEquipResultMsg equipResultMsg : EEquipResultMsg.values()) {
            if (key.contains(equipResultMsg.code)) {
                value = equipResultMsg.value;
            }
        }

        return value;
    }

    EEquipResultMsg(String code, String value) {
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
