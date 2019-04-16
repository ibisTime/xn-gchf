package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631812ReqData {
    // 企业统一社会信用代码
    @NotBlank
    private String corpCode;

    // 企业名称
    @NotBlank
    private String corpName;

    // 班组名称
    @NotBlank
    private String teamName;

    // 发放工资的月份
    private String payMonth;

    // 工人姓名
    @NotBlank
    private String workerName;

    // 证件号码
    @NotBlank
    private String idCardNumber;

    // 出勤天数
    private String days;

    // 总工时
    private String workHours;

    // 工人工资卡号
    @NotBlank
    private String payRollBankCardNumber;

    // 工人工资卡银行代码
    @NotBlank
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
    @NotBlank
    private String actualAmount;

    // 是否为补发
    private String isBackPay;

    // 发放日期
    private String backPayMonth;

    // 第三方工资单编号
    @NotBlank
    private String thirdPayRollCode;

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

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getPayMonth() {
        return payMonth;
    }

    public void setPayMonth(String payMonth) {
        this.payMonth = payMonth;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
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
