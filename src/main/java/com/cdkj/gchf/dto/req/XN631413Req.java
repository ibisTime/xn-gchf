package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631413Req {

    // （必填）编号
    @NotBlank
    private String code;

    // （必填）联系方式
    @NotBlank
    private String mobile;

    // （必填）免冠照片
    @NotBlank(message = "免冠照片不能为空")
    private String pict1;

    // （必填）手持身份张照片
    @NotBlank(message = "手持身份张照片不能为空")
    private String pict2;

    // （必填）身份证正反面照片+签名
    @NotBlank(message = "身份证正反面照片+签名不能为空")
    private String pict3;

    // （必填）更新人
    private String updater;

    // （必填）银行别称
    @NotBlank
    private String bankCode;

    // （必填）银行名称
    @NotBlank
    private String bankName;

    // （必填）银行卡号
    @NotBlank
    private String bankcardNumber;

    // （必填）开户行
    @NotBlank(message = "开户行不能为空")
    private String subbranch;

    // （必填）合同照片
    private String contentPic;

    // 签约时间
    private String contractDatetime;

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

    public String getPict1() {
        return pict1;
    }

    public void setPict1(String pict1) {
        this.pict1 = pict1;
    }

    public String getPict2() {
        return pict2;
    }

    public void setPict2(String pict2) {
        this.pict2 = pict2;
    }

    public String getPict3() {
        return pict3;
    }

    public void setPict3(String pict3) {
        this.pict3 = pict3;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankcardNumber() {
        return bankcardNumber;
    }

    public void setBankcardNumber(String bankcardNumber) {
        this.bankcardNumber = bankcardNumber;
    }

    public String getSubbranch() {
        return subbranch;
    }

    public void setSubbranch(String subbranch) {
        this.subbranch = subbranch;
    }

    public String getContentPic() {
        return contentPic;
    }

    public void setContentPic(String contentPic) {
        this.contentPic = contentPic;
    }

    public String getContractDatetime() {
        return contractDatetime;
    }

    public void setContractDatetime(String contractDatetime) {
        this.contractDatetime = contractDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
