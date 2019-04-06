package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631810Req {
    @NotBlank
    private String userId;

    @NotBlank
    private String code;

    private String days;

    private String workHours;

    private String totalPayAmount;

    private String actualAmount;

    private String isBackPay;

    private String balanceDate;

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

    public String getThirdPayRollCode() {
        return thirdPayRollCode;
    }

    public void setThirdPayRollCode(String thirdPayRollCode) {
        this.thirdPayRollCode = thirdPayRollCode;
    }

}
