package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631770ReqDetail {

    // 证件类型
    private String idCardType;

    // 证件号码
    private String idCardNumber;

    // 人员编号
    private String workerCode;

    /**
     * 项目人员银行卡
     */
    @NotBlank
    private String workerBankCard;

    /**
     * 所在企业银行卡
     */
    @NotBlank
    private String corpBankCard;

    // 出勤天数
    private String days;

    // 总工时
    private String workHours;


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

    public String getWorkerCode() {
        return workerCode;
    }

    public void setWorkerCode(String workerCode) {
        this.workerCode = workerCode;
    }

    public String getWorkerBankCard() {
        return workerBankCard;
    }

    public void setWorkerBankCard(String workerBankCard) {
        this.workerBankCard = workerBankCard;
    }

    public String getCorpBankCard() {
        return corpBankCard;
    }

    public void setCorpBankCard(String corpBankCard) {
        this.corpBankCard = corpBankCard;
    }
}
