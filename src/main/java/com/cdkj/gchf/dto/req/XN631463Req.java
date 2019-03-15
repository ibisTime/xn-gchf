package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改入职信息
 * @author: silver 
 * @since: 2018年7月13日 上午11:47:02 
 * @history:
 */
public class XN631463Req {
    // （必填）编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // （必填）项目编码
    @NotBlank(message = "项目编号不能为空")
    private String projectCode;

    // （必填）所属部门
    @NotBlank(message = "所属部门编号")
    private String departmentCode;

    // （必填）类别
    @NotBlank(message = "类别不能为空")
    private String type;

    // （必填）职位
    @NotBlank(message = "职位不能为空")
    private String position;

    // （必填）薪酬
    @NotBlank(message = "薪酬不能为空")
    private String salary;

    // （必填）迟到早退扣款金额
    @NotBlank(message = "迟到早退扣款金额不能为空")
    private String cutAmount;

    // 入职时间
    private String joinDatetime;

    // 银行行别
    private String bankCode;

    // 银行名称
    private String bankName;

    // 开户支行
    private String subbranch;

    // 银行卡号
    private String bankcardNumber;

    // （必填）更新人
    @NotBlank(message = "更新不能为空")
    private String updater;

    // （选填）备注
    private String remark;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getType() {
        return type;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
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

    public String getCutAmount() {
        return cutAmount;
    }

    public void setCutAmount(String cutAmount) {
        this.cutAmount = cutAmount;
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

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSubbranch() {
        return subbranch;
    }

    public void setSubbranch(String subbranch) {
        this.subbranch = subbranch;
    }

    public String getBankcardNumber() {
        return bankcardNumber;
    }

    public void setBankcardNumber(String bankcardNumber) {
        this.bankcardNumber = bankcardNumber;
    }

    public String getJoinDatetime() {
        return joinDatetime;
    }

    public void setJoinDatetime(String joinDatetime) {
        this.joinDatetime = joinDatetime;
    }

}
