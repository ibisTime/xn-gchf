package com.cdkj.gchf.domain;

import java.util.Date;

import com.cdkj.gchf.dao.base.ABaseDO;

/**
* 平台用户
* @author: chenshan 
* @since: 2018-04-30 22:00:38
* @history:
*/
public class Salary extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 消息编号
    private String messageCode;

    // 员工编号
    private String staffCode;

    // 项目编号
    private String projectCode;

    // 所属月份
    private Integer month;

    // 应发工资
    private Long shouldAmount;

    // 实发工资
    private Long factAmount;

    // 已发放金额
    private Long payAmount;

    // 扣款金额
    private Long cutAmount;

    // 扣款说明
    private String cutNote;

    // 税费
    private Long tax;

    // 迟到天数
    private Integer delayDays;

    // 早退天数
    private Integer earlyDays;

    // 请假天数
    private Double leavingDays;

    // 创建时间
    private Date createDatetime;

    // 状态
    private String status;

    // 审核人
    private String approveUser;

    // 审核时间
    private Date approveDatetime;

    // 审核说明
    private String approveNot;

    // 备注
    private String remark;

    // 最近一次发放时间
    private Date latePayDatetime;

    // **********db***************

    private String keyword;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public void setCutNote(String cutNote) {
        this.cutNote = cutNote;
    }

    public String getCutNote() {
        return cutNote;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setApproveUser(String approveUser) {
        this.approveUser = approveUser;
    }

    public String getApproveUser() {
        return approveUser;
    }

    public void setApproveNot(String approveNot) {
        this.approveNot = approveNot;
    }

    public String getApproveNot() {
        return approveNot;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public Long getShouldAmount() {
        return shouldAmount;
    }

    public void setShouldAmount(Long shouldAmount) {
        this.shouldAmount = shouldAmount;
    }

    public Long getFactAmount() {
        return factAmount;
    }

    public void setFactAmount(Long factAmount) {
        this.factAmount = factAmount;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public Long getCutAmount() {
        return cutAmount;
    }

    public void setCutAmount(Long cutAmount) {
        this.cutAmount = cutAmount;
    }

    public Long getTax() {
        return tax;
    }

    public void setTax(Long tax) {
        this.tax = tax;
    }

    public Integer getDelayDays() {
        return delayDays;
    }

    public void setDelayDays(Integer delayDays) {
        this.delayDays = delayDays;
    }

    public Integer getEarlyDays() {
        return earlyDays;
    }

    public void setEarlyDays(Integer earlyDays) {
        this.earlyDays = earlyDays;
    }

    public Double getLeavingDays() {
        return leavingDays;
    }

    public void setLeavingDays(Double leavingDays) {
        this.leavingDays = leavingDays;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getApproveDatetime() {
        return approveDatetime;
    }

    public void setApproveDatetime(Date approveDatetime) {
        this.approveDatetime = approveDatetime;
    }

    public Date getLatePayDatetime() {
        return latePayDatetime;
    }

    public void setLatePayDatetime(Date latePayDatetime) {
        this.latePayDatetime = latePayDatetime;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
