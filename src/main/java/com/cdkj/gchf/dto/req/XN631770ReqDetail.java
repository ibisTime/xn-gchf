package com.cdkj.gchf.dto.req;

import java.math.BigDecimal;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class XN631770ReqDetail {
    // 证件类型
    @NotBlank
    private String idcardType;

    // 证件号码
    @NotBlank
    private String idcardNumber;

    // 出勤天数
    private Integer days;

    // 总工时
    private BigDecimal workHours;

    // 工人工资卡号
    @NotBlank
    private String payRollBankCardNumber;

    // 工人工资卡银行代码
    private String payRollBankCode;

    // 工人工资卡开户行名称
    @NotBlank
    private String payRollBankName;

    // 工资代发银行卡号
    @NotBlank
    private String payBankCardNumber;

    // 工资代发银行代码
    @NotBlank
    private String payBankCode;

    // 工资代发开户行名称
    @NotBlank
    private String payBankName;

    // 应发金额
    @NotBlank
    private String totalPayAmount;

    // 实发金额
    private BigDecimal actualAmount;

    // 是否为补发
    @Min(0)
    private String isBackPay;

    // 发放日期
    @NotBlank
    private String balanceDate;

    // 补发月份
    private String backPayMonth;

    // 第三方工资单编号
    @NotBlank
    private String thirdPayRollCode;

    public String getIdCardNumber() {
        return idcardNumber;
    }

    public void setIdCardNumber(String idcardNumber) {
        this.idcardNumber = idcardNumber;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public BigDecimal getWorkHours() {
        return workHours;
    }

    public void setWorkHours(BigDecimal workHours) {
        this.workHours = workHours;
    }

    public String getPayRollBankCardNumber() {
        return payRollBankCardNumber;
    }

    public void setPayRollBankCardNumber(String payRollBankCardNumber) {
        this.payRollBankCardNumber = payRollBankCardNumber;
    }

    public String getPayRollBankCode() {
        return payRollBankCode;
    }

    public void setPayRollBankCode(String payRollBankCode) {
        this.payRollBankCode = payRollBankCode;
    }

    public String getPayRollBankName() {
        return payRollBankName;
    }

    public void setPayRollBankName(String payRollBankName) {
        this.payRollBankName = payRollBankName;
    }

    public String getPayBankCardNumber() {
        return payBankCardNumber;
    }

    public void setPayBankCardNumber(String payBankCardNumber) {
        this.payBankCardNumber = payBankCardNumber;
    }

    public String getPayBankCode() {
        return payBankCode;
    }

    public void setPayBankCode(String payBankCode) {
        this.payBankCode = payBankCode;
    }

    public String getPayBankName() {
        return payBankName;
    }

    public void setPayBankName(String payBankName) {
        this.payBankName = payBankName;
    }

    public String getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(String totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getIsBackPay() {
        return isBackPay;
    }

    public void setIsBackPay(String isBackPay) {
        this.isBackPay = isBackPay;
    }

    public String getBalanceDate() {
        return balanceDate;
    }

    public void setBalanceDate(String balanceDate) {
        this.balanceDate = balanceDate;
    }

    public String getBackPayMonth() {
        return backPayMonth;
    }

    public void setBackPayMonth(String backPayMonth) {
        this.backPayMonth = backPayMonth;
    }

    public String getThirdPayRollCode() {
        return thirdPayRollCode;
    }

    public void setThirdPayRollCode(String thirdPayRollCode) {
        this.thirdPayRollCode = thirdPayRollCode;
    }

    public String getIdcardType() {
        return idcardType;
    }

    public void setIdcardType(String idcardType) {
        this.idcardType = idcardType;
    }

    public String getIdcardNumber() {
        return idcardNumber;
    }

    public void setIdcardNumber(String idcardNumber) {
        this.idcardNumber = idcardNumber;
    }

}
