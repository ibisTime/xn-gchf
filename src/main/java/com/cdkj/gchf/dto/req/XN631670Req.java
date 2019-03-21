package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631670Req {
    // userId
    @NotBlank
    private String userId;

    // 项目编码
    @NotBlank
    private String projectCode;

    // 项目名称
    @NotBlank
    private String projectName;

    // 参建单位信用编号
    @NotBlank
    private String corpCode;

    // 参建单位信用名称
    @NotBlank
    private String corpName;

    // 身份证号码
    @NotBlank
    private String idCardNumber;

    // 合同编号
    @NotBlank
    private String contractCode;

    // 合同期限类型
    @NotBlank
    private String contractPeriodType;

    // 开始时间
    @NotBlank
    private String startDate;

    // 结束时间
    @NotBlank
    private String endDate;

    private String unit;

    private String unitPrice;

    private String isUpload;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

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

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getContractPeriodType() {
        return contractPeriodType;
    }

    public void setContractPeriodType(String contractPeriodType) {
        this.contractPeriodType = contractPeriodType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getIsUpload() {
        return isUpload;
    }

    public void setIsUpload(String isUpload) {
        this.isUpload = isUpload;
    }

}
