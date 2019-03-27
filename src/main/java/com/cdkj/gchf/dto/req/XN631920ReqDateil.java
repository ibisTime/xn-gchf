package com.cdkj.gchf.dto.req;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 上传人员工资
 * @author: silver 
 * @since: Mar 13, 2019 1:34:10 PM 
 * @history:
 */
public class XN631920ReqDateil {

    // 证件类型
    @NotBlank
    private String idCardType;

    // 证件号码
    @NotBlank
    private String idCardNumber;

    // 出勤天数
    private Integer days;

    // 总工时
    private BigDecimal workHours;

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
    @DecimalMin("0")
    private BigDecimal totalPayAmount;

    // 实发金额
    @DecimalMin("0")
    private BigDecimal actualAmount;

    // 是否是补发
    @NotBlank
    private Integer isBackPay;

    // 发放日期
    @NotBlank
    private String balanceDate;

    // 第三方工资单编号
    @NotBlank
    private String thirdPayRollCode;

    /****DB Properties****/
    // 工人名称
    private String workerName;

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

    public BigDecimal getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(BigDecimal totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Integer getIsBackPay() {
        return isBackPay;
    }

    public void setIsBackPay(Integer isBackPay) {
        this.isBackPay = isBackPay;
    }

    public String getBalanceDate() {
        return balanceDate;
    }

    public void setBalanceDate(String balanceDate) {
        this.balanceDate = balanceDate;
    }

    public String getThirdPayRollCode() {
        return thirdPayRollCode;
    }

    public void setThirdPayRollCode(String thirdPayRollCode) {
        this.thirdPayRollCode = thirdPayRollCode;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

}
