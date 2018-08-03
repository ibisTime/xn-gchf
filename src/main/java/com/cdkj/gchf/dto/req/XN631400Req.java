package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 合同录入
 * @author: nyc 
 * @since: 2018年4月29日 下午10:21:06 
 * @history:
 */
public class XN631400Req {
    // （必填）雇佣编号
    @NotBlank(message = "雇佣编号不能为空")
    private String employCode;

    // （必填）合同照片
    @NotBlank(message = "合同照片不能为空")
    private String contentPic;

    // （必填）更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    // （选填）备注
    private String remark;

    public String getContentPic() {
        return contentPic;
    }

    public void setContentPic(String contentPic) {
        this.contentPic = contentPic;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getEmployCode() {
        return employCode;
    }

    public void setEmployCode(String employCode) {
        this.employCode = employCode;
    }

}
