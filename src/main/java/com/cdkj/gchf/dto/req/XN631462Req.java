package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 离职
 * @author: nyc 
 * @since: 2018年4月29日 下午7:32:21 
 * @history:
 */
public class XN631462Req {

    // （必填） 员工编号
    @NotBlank(message = "编码不能为空")
    private String staffCode;

    // （必填） 项目编号
    @NotBlank(message = "编码不能为空")
    private String projectCode;

    // （必填）离职时间
    @NotBlank(message = "离职时间不能为空")
    private String leavingDatetime;

    // （必填）更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    // 备注
    private String remark;

    public String getStaffCode() {
        return staffCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
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

    public String getLeavingDatetime() {
        return leavingDatetime;
    }

    public void setLeavingDatetime(String leavingDatetime) {
        this.leavingDatetime = leavingDatetime;
    }

}
