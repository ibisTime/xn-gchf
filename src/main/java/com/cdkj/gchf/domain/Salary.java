
package com.cdkj.gchf.domain;

import java.util.Date;
import java.util.List;

import com.cdkj.gchf.dao.base.ABaseDO;

/**
* 工资条
* @author: nyc
* @since: 2018-04-30 22:00:38
* @history:
*/
public class Salary extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 消息编号
    private String messageCode;

    // 项目编号
    private String projectCode;

    // 项目编号
    private String projectName;

    // 员工编号
    private String staffCode;

    // 员工姓名
    private String staffName;

    // 所属月份
    private String month;

    // 请假天数（根据请假情况程序统计出来的）
    private Integer leavingDays;

    // 当月正常考勤的天数（即考勤记录isNormal是1的记录条数）
    private Integer attendanceDays;

    // 迟到小时数（根据考勤情况程序统计出来的）
    private Integer delayHours;

    // 早退小时数（根据考勤情况程序统计出来的）
    private Integer earlyHours;

    // 应发工资（程序计算来的：attendanceDays*日薪-（delayHours+earlyHours）*扣款时薪）
    private Long shouldAmount;

    // 状态
    private String status;

    // 创建时间
    private Date createDatetime;

    // 扣减金额（业主手动调整参数之一）
    private Long cutAmount;

    // 奖励金额(业主手动调整参数之一)
    private Long awardAmount;

    // 税费（业主手动调整参数之一）
    private Long tax;

    // 申请调整的人
    private String applyUser;

    // 申请调整时间
    private Date applyDatetime;

    // 申请调整说明
    private String applyNote;

    // 审核人
    private String approveUser;

    // 审核时间
    private Date approveDatetime;

    // 审核说明
    private String approveNote;

    // 应发工资（业主修改之后的最终应发金额：shouldAmount-cutAmount+awardAmount-tax）
    private Long factAmount;

    // 备注（针对factAmount的说明）
    private String factAmountRemark;

    // 已发工资（银行回填，即务工人员到手的工资）
    private Long payAmount;

    // 补发工资（应发薪资 - 银行实发薪资）
    private Long supplyAmount;

    // 最近一次发放时间
    private Date latePayDatetime;

    // **********db***************

    // 工资卡号
    private String bankcardNumber;

    // 关键字
    private String keyword;

    // 工资卡
    private BankCard bankCard;

    // 公司账户
    private CompanyCard companyCard;

    // 用户类型
    private String kind;

    // 员工手机号
    private String staffMobile;

    // 员工身份证号
    private String staffIdNo;

    // 状态类型
    private List<String> statusList;

    // 项目编号List
    private List<String> projectCodeList;

    // 隶属上级
    private String upUserName;

    // 申请调整的人
    private String applyUserName;

    // 审核人
    private String approveUserName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getLeavingDays() {
        return leavingDays;
    }

    public void setLeavingDays(Integer leavingDays) {
        this.leavingDays = leavingDays;
    }

    public Integer getAttendanceDays() {
        return attendanceDays;
    }

    public void setAttendanceDays(Integer attendanceDays) {
        this.attendanceDays = attendanceDays;
    }

    public Integer getDelayHours() {
        return delayHours;
    }

    public void setDelayHours(Integer delayHours) {
        this.delayHours = delayHours;
    }

    public Integer getEarlyHours() {
        return earlyHours;
    }

    public void setEarlyHours(Integer earlyHours) {
        this.earlyHours = earlyHours;
    }

    public Long getShouldAmount() {
        return shouldAmount;
    }

    public void setShouldAmount(Long shouldAmount) {
        this.shouldAmount = shouldAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Long getCutAmount() {
        return cutAmount;
    }

    public void setCutAmount(Long cutAmount) {
        this.cutAmount = cutAmount;
    }

    public Long getAwardAmount() {
        return awardAmount;
    }

    public void setAwardAmount(Long awardAmount) {
        this.awardAmount = awardAmount;
    }

    public Long getTax() {
        return tax;
    }

    public void setTax(Long tax) {
        this.tax = tax;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public Date getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(Date applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    public String getApplyNote() {
        return applyNote;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote;
    }

    public String getApproveUser() {
        return approveUser;
    }

    public void setApproveUser(String approveUser) {
        this.approveUser = approveUser;
    }

    public Date getApproveDatetime() {
        return approveDatetime;
    }

    public void setApproveDatetime(Date approveDatetime) {
        this.approveDatetime = approveDatetime;
    }

    public String getApproveNote() {
        return approveNote;
    }

    public void setApproveNote(String approveNote) {
        this.approveNote = approveNote;
    }

    public Long getFactAmount() {
        return factAmount;
    }

    public void setFactAmount(Long factAmount) {
        this.factAmount = factAmount;
    }

    public String getFactAmountRemark() {
        return factAmountRemark;
    }

    public void setFactAmountRemark(String factAmountRemark) {
        this.factAmountRemark = factAmountRemark;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public Long getSupplyAmount() {
        return supplyAmount;
    }

    public void setSupplyAmount(Long supplyAmount) {
        this.supplyAmount = supplyAmount;
    }

    public Date getLatePayDatetime() {
        return latePayDatetime;
    }

    public void setLatePayDatetime(Date latePayDatetime) {
        this.latePayDatetime = latePayDatetime;
    }

    public String getBankcardNumber() {
        return bankcardNumber;
    }

    public void setBankcardNumber(String bankcardNumber) {
        this.bankcardNumber = bankcardNumber;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public BankCard getBankCard() {
        return bankCard;
    }

    public void setBankCard(BankCard bankCard) {
        this.bankCard = bankCard;
    }

    public CompanyCard getCompanyCard() {
        return companyCard;
    }

    public void setCompanyCard(CompanyCard companyCard) {
        this.companyCard = companyCard;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getStaffMobile() {
        return staffMobile;
    }

    public void setStaffMobile(String staffMobile) {
        this.staffMobile = staffMobile;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public List<String> getProjectCodeList() {
        return projectCodeList;
    }

    public void setProjectCodeList(List<String> projectCodeList) {
        this.projectCodeList = projectCodeList;
    }

    public String getUpUserName() {
        return upUserName;
    }

    public void setUpUserName(String upUserName) {
        this.upUserName = upUserName;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public String getApproveUserName() {
        return approveUserName;
    }

    public void setApproveUserName(String approveUserName) {
        this.approveUserName = approveUserName;
    }

    public String getStaffIdNo() {
        return staffIdNo;
    }

    public void setStaffIdNo(String staffIdNo) {
        this.staffIdNo = staffIdNo;
    }

}
