package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改工资条
 * @author: nyc 
 * @since: 2018年5月1日 上午11:48:15 
 * @history:
 */
public class XN631442Req {

    // （必填）编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // （必填）迟到天数
    @NotBlank(message = "迟到天数不能为空")
    private String delayDays;

    // （必填）早退天数
    @NotBlank(message = "早退天数不能为空")
    private String earlyDays;

    // （必填）请假天数
    @NotBlank(message = "请假天数不能为空")
    private String leavingDays;

    // （必填）扣减金额
    @NotBlank(message = "扣减金额不能为空")
    private String cutAmount2;

    // （必填）奖励金额
    @NotBlank(message = "奖励金额不能为空")
    private String awardAmount;

    // （必填）扣款说明
    @NotBlank(message = "扣款说明不能为空")
    private String cutNote;

    // （必填）税费
    @NotBlank(message = "税费不能为空")
    private String tax;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDelayDays() {
        return delayDays;
    }

    public void setDelayDays(String delayDays) {
        this.delayDays = delayDays;
    }

    public String getEarlyDays() {
        return earlyDays;
    }

    public void setEarlyDays(String earlyDays) {
        this.earlyDays = earlyDays;
    }

    public String getLeavingDays() {
        return leavingDays;
    }

    public void setLeavingDays(String leavingDays) {
        this.leavingDays = leavingDays;
    }

    public String getCutAmount2() {
        return cutAmount2;
    }

    public String getAwardAmount() {
        return awardAmount;
    }

    public void setCutAmount2(String cutAmount2) {
        this.cutAmount2 = cutAmount2;
    }

    public void setAwardAmount(String awardAmount) {
        this.awardAmount = awardAmount;
    }

    public String getCutNote() {
        return cutNote;
    }

    public void setCutNote(String cutNote) {
        this.cutNote = cutNote;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

}
