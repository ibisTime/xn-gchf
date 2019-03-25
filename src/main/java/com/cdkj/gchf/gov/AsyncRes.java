package com.cdkj.gchf.gov;

public class AsyncRes {

    // 处理代码
    private String code;

    // 处理结果
    private String result;

    // 状态
    private String status;

    // 处理消息
    private String message;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public AsyncRes(String code, String result, String status, String message) {
        super();
        this.code = code;
        this.result = result;
        this.status = status;
        this.message = message;
    }

}
