package com.cdkj.gchf.dto.req;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

public class XN631772Req {
    // 用户id
    @NotBlank
    private String userId;

    // 编号
    @NotBlank
    private String code;

    @NotBlank
    private String payRollDetailCode;

    // 出勤天数
    private Integer days;

    // 工作时长
    private BigDecimal workHours;

    // 应发金额
    private BigDecimal totalPayAmount;

    // 是否是补发
    private Integer isBackPay;

    // 发放日期
    private Date balanceDate;

    // 第三方工资单编号
    private String thirdPayRollCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public BigDecimal getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(BigDecimal totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }

    public Integer getIsBackPay() {
        return isBackPay;
    }

    public void setIsBackPay(Integer isBackPay) {
        this.isBackPay = isBackPay;
    }

    public Date getBalanceDate() {
        return balanceDate;
    }

    public void setBalanceDate(Date balanceDate) {
        this.balanceDate = balanceDate;
    }

    public String getThirdPayRollCode() {
        return thirdPayRollCode;
    }

    public void setThirdPayRollCode(String thirdPayRollCode) {
        this.thirdPayRollCode = thirdPayRollCode;
    }

    public String getPayRollDetailCode() {
        return payRollDetailCode;
    }

    public void setPayRollDetailCode(String payRollDetailCode) {
        this.payRollDetailCode = payRollDetailCode;
    }

}
