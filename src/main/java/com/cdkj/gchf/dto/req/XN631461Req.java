package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 请假
 * @author: nyc 
 * @since: 2018年4月29日 下午7:32:21 
 * @history:
 */
public class XN631461Req {

    // （必填） 员工编号
    @NotBlank(message = "编码不能为空")
    private String staffCode;

    // （必填） 项目编号
    @NotBlank(message = "编码不能为空")
    private String projectCode;

    // （必填）开始时间
    @NotBlank(message = "开始时间不能为空")
    private String startDatetime;

    // （必填）请假天数
    @NotBlank(message = "请假天数不能为空")
    private String lastLeavingDays;

    // （必填）更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    // 备注
    private String remark;

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(String startDatetime) {
        this.startDatetime = startDatetime;
    }

    public String getLastLeavingDays() {
        return lastLeavingDays;
    }

    public void setLastLeavingDays(String lastLeavingDays) {
        this.lastLeavingDays = lastLeavingDays;
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

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

}
