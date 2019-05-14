package com.cdkj.gchf.humanfaces.enums;

/**
 * @author old3
 */

public enum EWorkerUploadStatus {
    //云端人员上传状态
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
