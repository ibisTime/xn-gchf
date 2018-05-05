package com.cdkj.gchf.domain;

import com.cdkj.gchf.dao.base.ABaseDO;

/**
* 平台用户
* @author: nyc 
* @since: 2018-05-03 17:10:51
* @history:
*/
public class Report extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 项目编号
    private String projectCode;

    // 项目名称
    private String projectName;

    // 今日上工人数
    private Integer todayDays;

    // 上月实际发薪金额
    private Long lastMonthSalary;

    // 下月预计发薪金额
    private Long nextMonthSalary;

    // 累计发薪金额
    private Long totalSalary;

    // 目前在职人数
    private Integer staffOn;

    // 累计入职人数
    private Integer staffIn;

    // 累计离职人数
    private Integer staffOut;

    // 累计请假人次
    private Integer leavingDays;

    // 累计出工人次
    private Integer workingDays;

    // 备注
    private String remark;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public Integer getTodayDays() {
        return todayDays;
    }

    public void setTodayDays(Integer todayDays) {
        this.todayDays = todayDays;
    }

    public Long getLastMonthSalary() {
        return lastMonthSalary;
    }

    public void setLastMonthSalary(Long lastMonthSalary) {
        this.lastMonthSalary = lastMonthSalary;
    }

    public Long getNextMonthSalary() {
        return nextMonthSalary;
    }

    public void setNextMonthSalary(Long nextMonthSalary) {
        this.nextMonthSalary = nextMonthSalary;
    }

    public Long getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(Long totalSalary) {
        this.totalSalary = totalSalary;
    }

    public Integer getStaffOn() {
        return staffOn;
    }

    public void setStaffOn(Integer staffOn) {
        this.staffOn = staffOn;
    }

    public Integer getStaffIn() {
        return staffIn;
    }

    public void setStaffIn(Integer staffIn) {
        this.staffIn = staffIn;
    }

    public Integer getStaffOut() {
        return staffOut;
    }

    public void setStaffOut(Integer staffOut) {
        this.staffOut = staffOut;
    }

    public Integer getLeavingDays() {
        return leavingDays;
    }

    public void setLeavingDays(Integer leavingDays) {
        this.leavingDays = leavingDays;
    }

    public Integer getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(Integer workingDays) {
        this.workingDays = workingDays;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

}
