/**
 * @Title ECheckResult.java
 * @Package com.ibis.account.enums
 * @Description
 * @author miyb
 * @date 2015-2-26 下午2:58:54
 * @version V1.0
 */
package com.cdkj.gchf.enums;

/**
 * @author: miyb
 * @since: 2015-2-26 下午2:58:54
 * @history:
 */
public enum EAttendanceDirection {
    IN("01", "入场"),

    OUT("02", "出场");

    EAttendanceDirection(String code, String value) {
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
