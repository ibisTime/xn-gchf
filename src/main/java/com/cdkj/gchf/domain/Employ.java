package com.cdkj.gchf.domain;

import java.util.Date;

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

    // 项目名称
    private String projectName;

    // 员工编号
    private String staffCode;

    // 员工手机号
    private String staffMobile;

    // 类别
    private String type;

    // 所在职位
    private String position;

    // 上级编号
    private String upUser;

    // 薪酬
    private Long salary;

    // 状态
    private String status;

    // 入职时间
    private Date joinDatetime;

    // 离职时间
    private Date leavingDatetime;

    // 最近一次请假开始时间
    private Date startDatetime;

    // 最近一次请假结束时间
    private Date endDatetime;

    // 请假天数
    private Double days;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // ***********db**************
    private String keyword;

    private User user;

    private Staff staff;

    private String upUserName;

    private String updateName;

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

    public void setUpUser(String upUser) {
        this.upUser = upUser;
    }

    public String getUpUser() {
        return upUser;
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

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Double getDays() {
        return days;
    }

    public void setDays(Double days) {
        this.days = days;
    }

    public String getUpUserName() {
        return upUserName;
    }

    public void setUpUserName(String upUserName) {
        this.upUserName = upUserName;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

}
