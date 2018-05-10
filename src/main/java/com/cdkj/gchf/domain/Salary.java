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

    // 员工编号
    private String staffCode;

    // 公司编号
    private String companyCode;

    // 公司名称
    private String companyName;

    // 项目编号
    private String projectCode;

    // 项目编号
    private String projectName;

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

    // 正常上工天数
    private Double normalDays;

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
    private String approveNote;

    // 备注
    private String remark;

    // 最近一次发放时间
    private Date latePayDatetime;

    // **********db***************

    private String keyword;

    // 工资卡
    private BankCard bankCard;

    // 公司账户
    private CompanyCard companyCard;

    // 月累积薪资
    private Long totalFactAmount;

    // 用户类型
    private String kind;

    // 状态类型
    private List<String> statusList;

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
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

    public void setApproveNote(String approveNote) {
        this.approveNote = approveNote;
    }

    public String getApproveNote() {
        return approveNote;
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

    public Long getTotalFactAmount() {
        return totalFactAmount;
    }

    public void setTotalFactAmount(Long totalFactAmount) {
        this.totalFactAmount = totalFactAmount;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public Double getNormalDays() {
        return normalDays;
    }

    public void setNormalDays(Double normalDays) {
        this.normalDays = normalDays;
    }

}
