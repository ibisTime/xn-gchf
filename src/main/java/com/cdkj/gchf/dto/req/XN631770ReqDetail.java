package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631770ReqDetail {

    // 证件类型
    private String idCardType;

    // 证件号码
    private String idCardNumber;

    // 出勤天数
    private String days;

    // 总工时
    private String workHours;

    // 工人工资卡号
    @NotBlank(message = "项目人员银行卡号不能为空")
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
    @NotBlank(message = "应发金额不能为空")
    private String totalPayAmount;

    // 实发金额
    private String actualAmount;

    // 是否为补发
    private String isBackPay;

    // 发放日期
    @NotBlank(message = "发放日期不能为空")
    private String balanceDate;

    // 补发月份
    private String backPayMonth;

    // 第三方工资单编号
    @NotBlank
    private String thirdPayRollCode;

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

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getWorkHours() {
        return workHours;
    }

    public void setWorkHours(String workHours) {
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

    public String getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(String actualAmount) {
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

}
