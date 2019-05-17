package com.cdkj.gchf.humanfaces.enums;

/**
 * @author old3
 */

public enum EAttendancePicUploadStatus {
    //图片上传失败
    FAIL("-1", "失败"),
    //图片上传成功
    SUCCESS("1", "成功");

    EAttendancePicUploadStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;

    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
