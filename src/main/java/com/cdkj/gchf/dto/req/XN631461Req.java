package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 请假
 * @author: nyc 
 * @since: 2018年4月29日 下午7:32:21 
 * @history:
 */
public class XN631461Req {

    // （必填） 雇佣编号
    @NotBlank(message = "雇佣编号不能为空")
    private String employCode;

    // （必填）开始时间
    @NotBlank(message = "开始时间不能为空")
    private String startDatetime;

    // （必填）结束时间
    @NotBlank(message = "结束时间不能为空")
    private String endDatetime;

    // （必填）更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    // 备注
    private String remark;

    public String getEmployCode() {
        return employCode;
    }

    public void setEmployCode(String employCode) {
        this.employCode = employCode;
    }

    public String getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(String startDatetime) {
        this.startDatetime = startDatetime;
    }

    public String getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(String endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
