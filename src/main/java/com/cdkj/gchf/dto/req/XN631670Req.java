package com.cdkj.gchf.dto.req;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotBlank;

public class XN631670Req {
    // userId
    @NotBlank
    private String userId;

    // 项目编码
    @NotBlank(message = "项目编码不存在")
    private String projectCode;

    // 参建单位信用编号
    @NotBlank(message = "企业信息不存在")
    private String corpCode;

    // 员工编号
    private String workerCode;

    // 更新人
    private String updater;

    // 更新时间
    private String updateDatetime;

    // 备注
    private String remark;

    // 状态
    private String uploadStatus;

    // 合同编号
    @NotBlank
    private String contractCode;

    // 合同期限类型
    private Integer contractPeriodType;

    // 开始时间
    private String startDate;

    // 结束时间
    private String endDate;

    // 计量单位
    private Integer unit;

    // 计量单位
    private BigDecimal unitPrice;

    // 合同照片
    private String contentPic;

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

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

    public String getWorkerCode() {
        return workerCode;
    }

    public void setWorkerCode(String workerCode) {
        this.workerCode = workerCode;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(String updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public Integer getContractPeriodType() {
        return contractPeriodType;
    }

    public void setContractPeriodType(Integer contractPeriodType) {
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

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getContentPic() {
        return contentPic;
    }

    public void setContentPic(String contentPic) {
        this.contentPic = contentPic;
    }

}
