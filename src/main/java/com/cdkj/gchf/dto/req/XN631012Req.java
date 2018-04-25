package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631012Req {

    // 编号（必填）
    @NotBlank(message = "编号不能为空")
    private String id;

    // 值（必填）
    @NotBlank(message = "值不能为空")
    private String cvalue;

    // 修改人（必填）
    @NotBlank(message = "修改人不能为空")
    private String updater;

    // 备注（选填）
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCvalue() {
        return cvalue;
    }

    public void setCvalue(String cvalue) {
        this.cvalue = cvalue;
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
