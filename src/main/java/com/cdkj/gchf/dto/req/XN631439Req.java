package com.cdkj.gchf.dto.req;

public class XN631439Req {

    // 工资条编号
    private String code;

    // 发放金额
    private String payAmount;

    // 最后一次发放时间
    private String latePayDatetime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getLatePayDatetime() {
        return latePayDatetime;
    }

    public void setLatePayDatetime(String latePayDatetime) {
        this.latePayDatetime = latePayDatetime;
    }

}
