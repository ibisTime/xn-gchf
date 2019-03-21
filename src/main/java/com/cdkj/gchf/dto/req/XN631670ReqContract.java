package com.cdkj.gchf.dto.req;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class XN631670ReqContract {

    // 工人所属企业统一社会信用代码
    @NotBlank
    private String corpCode;

    // 工人所属企业名称
    @NotBlank
    private String corpName;

    // 证件类型
    @NotBlank
    private String idCardType;

    // 证件号码
    @NotBlank
    private String idCardNumber;

    // 合同期限类型
    @Min(0)
    private Integer contractPeriodType;

    // 生效日期
    @NotBlank
    private String startDate;

    // 失效日期
    @NotBlank
    private String endDate;

    // 计量单位
    private Integer unit;

    // 计量单价
    private BigDecimal unitPrice;

    // 合同附件
    private List<XN631670ReqAttachments> attachments;

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

    public String getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
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

    public List<XN631670ReqAttachments> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<XN631670ReqAttachments> attachments) {
        this.attachments = attachments;
    }

}
