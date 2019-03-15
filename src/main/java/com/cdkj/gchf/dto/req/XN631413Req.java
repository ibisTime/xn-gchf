package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 录入联系方式
 * @author: silver 
 * @since: 2018年7月12日 下午5:02:20 
 * @history:
 */
public class XN631413Req {
    // （必填）编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // （必填）联系方式
    @NotBlank
    private String mobile;

    // （必填）紧急联系人
    @NotBlank
    private String contacts;

    // （必填）紧急联系人电话
    @NotBlank
    private String contactsMobile;

    // （必填）更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    // （选填）备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactsMobile() {
        return contactsMobile;
    }

    public void setContactsMobile(String contactsMobile) {
        this.contactsMobile = contactsMobile;
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
