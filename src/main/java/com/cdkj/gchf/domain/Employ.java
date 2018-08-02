package com.cdkj.gchf.domain;

import java.util.Date;
import java.util.List;

import com.cdkj.gchf.dao.base.ABaseDO;

/**
* 雇佣表
* @author: chenshan 
* @since: 2018-04-29 19:17:32
* @history:
*/
public class Employ extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 项目编号
    private String projectCode;

    // 项目编号
    private String projectName;

    // 部门编号
    private String departmentCode;

    // 员工编号
    private String staffCode;

    // 员工姓名
    private String staffName;

    // 员工手机号
    private String staffMobile;

    // 类别(0直招工人/1劳务工人/2包工工人/3内勤人员)
    private String type;

    // 所在职位(0普工/1主管)
    private String position;

    // 薪酬（日薪）
    private Long salary;

    // 迟到早退扣款时薪
    private Long cutAmount;

    // 状态(0在职/1请假/2离职)
    private String status;

    // 入职时间
    private Date joinDatetime;

    // 离职时间
    private Date leavingDatetime;

    // 最近一次请假开始时间
    private Date startDatetime;

    // 最近一次请假天数
    private Integer lastLeavingDays;

    // 累积请假天数
    private Integer totalLeavingDays;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // 工资发放状态
    private String salaryStatus;

    // ***********db**************
    private String keyword;

    private Staff staff;

    private String updateName;

    private List<String> statusList;

    private List<String> projectCodeList;

    // 部门名称
    private String departmentName;

    // 部门负责人
    private String departmentLeader;

    // 部门负责人手机号
    private String departmentLeaderMobile;

    // 银行卡
    private BankCard bankCard;

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

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffMobile(String staffMobile) {
        this.staffMobile = staffMobile;
    }

    public String getStaffMobile() {
        return staffMobile;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Long getCutAmount() {
        return cutAmount;
    }

    public void setCutAmount(Long cutAmount) {
        this.cutAmount = cutAmount;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Date getJoinDatetime() {
        return joinDatetime;
    }

    public void setJoinDatetime(Date joinDatetime) {
        this.joinDatetime = joinDatetime;
    }

    public Date getLeavingDatetime() {
        return leavingDatetime;
    }

    public void setLeavingDatetime(Date leavingDatetime) {
        this.leavingDatetime = leavingDatetime;
    }

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public Integer getTotalLeavingDays() {
        return totalLeavingDays;
    }

    public void setTotalLeavingDays(Integer totalLeavingDays) {
        this.totalLeavingDays = totalLeavingDays;
    }

    public List<String> getProjectCodeList() {
        return projectCodeList;
    }

    public void setProjectCodeList(List<String> projectCodeList) {
        this.projectCodeList = projectCodeList;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getSalaryStatus() {
        return salaryStatus;
    }

    public void setSalaryStatus(String salaryStatus) {
        this.salaryStatus = salaryStatus;
    }

    public Integer getLastLeavingDays() {
        return lastLeavingDays;
    }

    public void setLastLeavingDays(Integer lastLeavingDays) {
        this.lastLeavingDays = lastLeavingDays;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public BankCard getBankCard() {
        return bankCard;
    }

    public void setBankCard(BankCard bankCard) {
        this.bankCard = bankCard;
    }

    public String getDepartmentLeader() {
        return departmentLeader;
    }

    public void setDepartmentLeader(String departmentLeader) {
        this.departmentLeader = departmentLeader;
    }

    public String getDepartmentLeaderMobile() {
        return departmentLeaderMobile;
    }

    public void setDepartmentLeaderMobile(String departmentLeaderMobile) {
        this.departmentLeaderMobile = departmentLeaderMobile;
    }

}
