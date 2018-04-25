package com.cdkj.gchf.dto.req;

public class XN630431Req {

    private String code; // 编号

    private String handler; // 处理人

    private String remark; // 备注

    private String result; // 处理结果（1通过，0不通过）

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
