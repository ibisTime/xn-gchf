package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 员工入职
 * @author: nyc 
 * @since: 2018年4月29日 下午7:32:21 
 * @history:
 */
public class XN631460Req {

    // （必填）项目编码
    @NotBlank(message = "项目编码不能为空")
    private String projectCode;

    // （必填）员工编号
    @NotBlank(message = "员工编号不能为空")
    private String staffCode;

    // （必填）类别
    @NotBlank(message = "类别不能为空")
    private String type;

    // （必填）职位
    @NotBlank(message = "职位不能为空")
    private String position;

    // （必填）薪酬
    @NotBlank(message = "薪酬不能为空")
    private String salary;

    // （必填）上级
    @NotBlank(message = "上级不能为空")
    private String upUser;

    // （必填）入职时间
    @NotBlank(message = "入职时间不能为空")
    private String joinDatetime;

    // （必填）更新人
    @NotBlank(message = "更新不能为空")
    private String updater;

    // （必填）扣款规则
    @NotBlank(message = "扣款规则不能为空")
    private String cutAmount;

    // （选填）备注
    private String remark;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getUpUser() {
        return upUser;
    }

    public void setUpUser(String upUser) {
        this.upUser = upUser;
    }

    public String getJoinDatetime() {
        return joinDatetime;
    }

    public void setJoinDatetime(String joinDatetime) {
        this.joinDatetime = joinDatetime;
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

    public String getCutAmount() {
        return cutAmount;
    }

    public void setCutAmount(String cutAmount) {
        this.cutAmount = cutAmount;
    }

}
