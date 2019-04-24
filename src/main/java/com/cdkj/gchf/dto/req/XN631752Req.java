package com.cdkj.gchf.dto.req;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

public class XN631752Req {
    // 用户id
    @NotBlank(message = "用户id不能为空")
    private String userId;

    // 编号
    private String code;

    // 业务类型
    private String businessType;

    // 业务编号
    private String businessSysNo;

    // 银行代码
    private String bankCode;

    // 银行支行名称
    private String bankName;

    // 支行名称
    private String subranch;

    // 银行账户
    private String bankNumber;

    // 银行联号
    private String bankLinkNumber;

    // 更新人id
    private String updater;

    // 更新时间(yyyy-MM-dd HH:mm:ss)
    private Date updateDatetime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBusinessSysNo() {
        return businessSysNo;
    }

    public void setBusinessSysNo(String businessSysNo) {
        this.businessSysNo = businessSysNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getBankLinkNumber() {
        return bankLinkNumber;
    }

    public void setBankLinkNumber(String bankLinkNumber) {
        this.bankLinkNumber = bankLinkNumber;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getSubranch() {
        return subranch;
    }

    public void setSubranch(String subranch) {
        this.subranch = subranch;
    }

}
