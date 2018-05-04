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
	private String todayDays;

	// 上月实际发薪金额
	private String lastMonthSalary;

	// 下月预计发薪金额
	private String nextMonthSalary;

	// 累计发薪金额
	private String totalSalary;

	// 目前在职人数
	private String staffOn;

	// 累计入职人数
	private String staffIn;

	// 累计离职人数
	private String staffOut;

	// 累计请假人次
	private String leavingDays;

	// 累计出工人次
	private String workingDays;

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

	public void setTodayDays(String todayDays) {
		this.todayDays = todayDays;
	}

	public String getTodayDays() {
		return todayDays;
	}

	public void setLastMonthSalary(String lastMonthSalary) {
		this.lastMonthSalary = lastMonthSalary;
	}

	public String getLastMonthSalary() {
		return lastMonthSalary;
	}

	public void setNextMonthSalary(String nextMonthSalary) {
		this.nextMonthSalary = nextMonthSalary;
	}

	public String getNextMonthSalary() {
		return nextMonthSalary;
	}

	public void setTotalSalary(String totalSalary) {
		this.totalSalary = totalSalary;
	}

	public String getTotalSalary() {
		return totalSalary;
	}

	public void setStaffOn(String staffOn) {
		this.staffOn = staffOn;
	}

	public String getStaffOn() {
		return staffOn;
	}

	public void setStaffIn(String staffIn) {
		this.staffIn = staffIn;
	}

	public String getStaffIn() {
		return staffIn;
	}

	public void setStaffOut(String staffOut) {
		this.staffOut = staffOut;
	}

	public String getStaffOut() {
		return staffOut;
	}

	public void setLeavingDays(String leavingDays) {
		this.leavingDays = leavingDays;
	}

	public String getLeavingDays() {
		return leavingDays;
	}

	public void setWorkingDays(String workingDays) {
		this.workingDays = workingDays;
	}

	public String getWorkingDays() {
		return workingDays;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

}