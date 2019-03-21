package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改企业信息
 * @author: silver 
 * @since: Mar 13, 2019 1:34:26 PM 
 * @history:
 */
public class XN631251Req {

    // 编号
    @NotBlank
    private String code;

    // 统一社会信用代码
    @NotBlank
    private String corpCode;

    // 企业名称
    @NotBlank
    private String corpName;

    // 企业登记注册类型
    private String corpType;

    // 工商营业执照注册号
    private String licenseNum;

    // 注册地区编码
    @NotBlank
    private String areaCode;

    // 企业营业地址
    @NotBlank
    private String address;

    // 邮政编码
    private String zipCode;

    // 法定代表人姓名
    private String legalMan;

    // 法定代表人职务
    private String legalManDuty;

    // 法定代表人职称
    private String legalManProTitle;

    // 法定代表人证件类型
    private String legalManIdcardType;

    // 法定代表人证件号码
    private String legalManIdcardNumber;

    // 注册资本
    private String regCapital;

    // 实收资本
    private String factRegCapital;

    // 资本币种
    private String capitalCurrencyType;

    // 注册日期
    @NotBlank
    private String registerDate;

    // 成立日期
    private String establishDate;

    // 办公电话
    private String officePhone;

    // 传真号码
    private String faxNumber;

    // 联系人姓名
    private String linkMan;

    // 联系人电话
    private String linkPhone;

    // 企业邮箱
    private String email;

    // 企业网址
    private String webSite;

    // 用户编号
    @NotBlank
    private String userId;

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getCorpType() {
        return corpType;
    }

    public void setCorpType(String corpType) {
        this.corpType = corpType;
    }

    public String getLicenseNum() {
        return licenseNum;
    }

    public void setLicenseNum(String licenseNum) {
        this.licenseNum = licenseNum;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getLegalMan() {
        return legalMan;
    }

    public void setLegalMan(String legalMan) {
        this.legalMan = legalMan;
    }

    public String getLegalManDuty() {
        return legalManDuty;
    }

    public void setLegalManDuty(String legalManDuty) {
        this.legalManDuty = legalManDuty;
    }

    public String getLegalManProTitle() {
        return legalManProTitle;
    }

    public void setLegalManProTitle(String legalManProTitle) {
        this.legalManProTitle = legalManProTitle;
    }

    public String getLegalManIdcardType() {
        return legalManIdcardType;
    }

    public void setLegalManIdcardType(String legalManIdcardType) {
        this.legalManIdcardType = legalManIdcardType;
    }

    public String getLegalManIdcardNumber() {
        return legalManIdcardNumber;
    }

    public void setLegalManIdcardNumber(String legalManIdcardNumber) {
        this.legalManIdcardNumber = legalManIdcardNumber;
    }

    public String getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(String regCapital) {
        this.regCapital = regCapital;
    }

    public String getFactRegCapital() {
        return factRegCapital;
    }

    public void setFactRegCapital(String factRegCapital) {
        this.factRegCapital = factRegCapital;
    }

    public String getCapitalCurrencyType() {
        return capitalCurrencyType;
    }

    public void setCapitalCurrencyType(String capitalCurrencyType) {
        this.capitalCurrencyType = capitalCurrencyType;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(String establishDate) {
        this.establishDate = establishDate;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
