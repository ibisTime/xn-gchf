package com.cdkj.gchf.domain;

import com.cdkj.gchf.dao.base.ABaseDO;

public class BankCardInfo extends ABaseDO {

    /**   
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = -5841005973218872505L;

    // 编号
    private String code;

    // 业务类型
    private String businessType;

    // 业务类型
    private Integer businessSysNo;

    // 银行支行名称
    private String bankName;

    // 银行支行名称
    private String bankNumber;

    // 银行支行名称
    private String bankLinkNumber;

    // 状态
    private String uploadStatus;

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

    public Integer getBusinessSysNo() {
        return businessSysNo;
    }

    public void setBusinessSysNo(Integer businessSysNo) {
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

    public String getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

}
