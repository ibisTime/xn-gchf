package com.cdkj.gchf.enums;

/**
 * 上传状态
 * @author: silver 
 * @since: Mar 21, 2019 2:01:14 PM 
 * @history:
 */
public enum EUploadStatus {
    TO_UPLOAD("0", "待上传"),

    UPLOAD_EDITABLE("1", "已上传可修改"),

    UPLOAD_UNEDITABLE("2", "已上传不可修改");

    EUploadStatus(String code, String value) {
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
