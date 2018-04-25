package com.cdkj.gchf.dto.req;

public class XN630404Req {

    // 编号（必填）
    private String code;

    // 最新修改人（必填）
    private String updater;

    // 备注（必填）
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
