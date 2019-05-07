package com.cdkj.gchf.enums;

/**
 * 考勤来源
 * @author: silver 
 * @since: May 7, 2019 8:30:15 PM 
 * @history:
 */
public enum EAttendanceSource {

    SYSTEM("1", "系统生成"),

    REAL_TIME("2", "实时数据");

    EAttendanceSource(String code, String value) {
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
