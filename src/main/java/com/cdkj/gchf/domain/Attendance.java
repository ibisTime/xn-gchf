package com.cdkj.gchf.domain;

import java.util.Date;
import java.util.List;

import com.cdkj.gchf.dao.base.ABaseDO;

/**
* 考勤
* @author: chenshan 
* @since: 2018-04-29 22:49:12
* @history:
*/
public class Attendance extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 雇佣编号
    private String employCode;

    // 项目编号
    private String projectCode;

    // 项目名称
    private String projectName;

    // 员工编号
    private String staffCode;

    // 员工姓名
    private String staffName;

    // 出工状态
    private String status;

    // 生成时间
    private Date createDatetime;

    // 上班时间
    private Date startDatetime;

    // 下班时间
    private Date endDatetime;

    // 结算时间
    private Date settleDatetime;

    // 相似度
    private String sim;

    // 设备编号
    private String terminalCode;

    // 备注
    private String remark;

    // ******************db*************

    // 关键字
    private String keyword;

    // 生成开始时间
    private Date createDatetimeStart;

    // 生成结束时间
    private Date createDatetimeEnd;

    // 项目编号List
    private List<String> projectCodeList;

    // 状态列表
    private List<String> statusList;

    // 考勤开始时间
    private Date attendanceStartDatetime;

    // 考勤结束时间
    private Date attendanceEndDatetime;

    // 考勤生成月份
    private String createMonth;

    // 考勤生成日期
    private String createDay;

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

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getStartDatetime() {
        return startDatetime;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    public Date getSettleDatetime() {
        return settleDatetime;
    }

    public void setSettleDatetime(Date settleDatetime) {
        this.settleDatetime = settleDatetime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Date getCreateDatetimeStart() {
        return createDatetimeStart;
    }

    public void setCreateDatetimeStart(Date createDatetimeStart) {
        this.createDatetimeStart = createDatetimeStart;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public String getSim() {
        return sim;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }

    public Date getCreateDatetimeEnd() {
        return createDatetimeEnd;
    }

    public void setCreateDatetimeEnd(Date createDatetimeEnd) {
        this.createDatetimeEnd = createDatetimeEnd;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public List<String> getProjectCodeList() {
        return projectCodeList;
    }

    public void setProjectCodeList(List<String> projectCodeList) {
        this.projectCodeList = projectCodeList;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String getEmployCode() {
        return employCode;
    }

    public void setEmployCode(String employCode) {
        this.employCode = employCode;
    }

    public Date getAttendanceStartDatetime() {
        return attendanceStartDatetime;
    }

    public void setAttendanceStartDatetime(Date attendanceStartDatetime) {
        this.attendanceStartDatetime = attendanceStartDatetime;
    }

    public Date getAttendanceEndDatetime() {
        return attendanceEndDatetime;
    }

    public void setAttendanceEndDatetime(Date attendanceEndDatetime) {
        this.attendanceEndDatetime = attendanceEndDatetime;
    }

    public String getCreateMonth() {
        return createMonth;
    }

    public void setCreateMonth(String createMonth) {
        this.createMonth = createMonth;
    }

    public String getCreateDay() {
        return createDay;
    }

    public void setCreateDay(String createDay) {
        this.createDay = createDay;
    }

}
