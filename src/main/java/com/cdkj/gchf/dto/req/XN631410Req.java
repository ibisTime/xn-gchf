package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631410Req {
    // （必填）员工编号
    private String staffCode;

    // （必填）项目编号
    @NotBlank(message = "项目编号不能为空")
    private String projectCode;

    // （必填）证件类型
    @NotBlank(message = "证件类型不能为空")
    private String idType;

    // （必填）身份证号
    @NotBlank(message = "身份证号不能为空")
    private String idNo;

    // （必填）联系方式
    @NotBlank(message = "联系方式不能为空")
    private String mobile;

    // （必填）姓名
    @NotBlank(message = "姓名不能为空")
    private String name;

    // （必填）免冠照片
    private String pict1;

    // （必填）手持身份张照片
    private String pict2;

    // （必填）身份证正反面照片+签名
    private String pict3;

    // （必填）更新人
    private String updater;

    // （必填）银行别称
    private String bankCode;

    // （必填）银行名称
    private String bankName;

    // （必填）银行卡号
    private String bankcardNumber;

    // （必填）开户行
    private String subbranch;

    // （必填）合同照片
    private String contentPic;

    // 签约时间
    private String contractDatetime;

    // 特征值
    private String feat;

    // （选填）备注
    private String remark;

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getContractDatetime() {
        return contractDatetime;
    }

    public void setContractDatetime(String contractDatetime) {
        this.contractDatetime = contractDatetime;
    }

    public String getContentPic() {
        return contentPic;
    }

    public void setContentPic(String contentPic) {
        this.contentPic = contentPic;
    }

    public String getFeat() {
        return feat;
    }

    public void setFeat(String feat) {
        this.feat = feat;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

}
