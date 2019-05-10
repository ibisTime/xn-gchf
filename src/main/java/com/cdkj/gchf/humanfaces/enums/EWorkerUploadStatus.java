package com.cdkj.gchf.humanfaces.enums;

public enum EWorkerUploadStatus {

    FAIL("-1", "失败"),

    SUCCESS("1", "成功");

    EWorkerUploadStatus(String code, String message) {
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
