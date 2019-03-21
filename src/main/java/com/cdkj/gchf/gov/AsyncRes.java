package com.cdkj.gchf.gov;

public class AsyncRes {

    // 处理结果
    private String result;

    // 状态
    private String status;

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

    public AsyncRes(String result, String status) {
        super();
        this.result = result;
        this.status = status;
    }

}
