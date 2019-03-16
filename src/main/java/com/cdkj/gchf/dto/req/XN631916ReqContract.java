package com.cdkj.gchf.dto.req;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 上传人员合同
 * @author: silver 
 * @since: Mar 13, 2019 1:34:10 PM 
 * @history:
 */
public class XN631916ReqContract {

    // 所属企业统一社会信用代码
    @NotBlank
    private String corpCode;

    // 所属企业名称
    @NotBlank
    private String corpName;

    // 证件类型
    @NotBlank
    private String idCardType;

    // 证件号码
    @NotBlank
    private String idCardNumber;

    // 合同期限类型
    @NotBlank
    private Integer contractPeriodType;

    // 开始日期
    @NotBlank
    private Date startDate;

    // 结束时期
    @NotBlank
    private Date endDate;

    // 计量单位
    private Integer unit;

    // 计量单位
    private BigDecimal unitPrice;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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

}
