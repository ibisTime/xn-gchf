
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

    // 项目编号
    private String projectCode;

    // 项目编号
    private String projectName;

    // 消息编号
    private String messageCode;

    // 员工编号
    private String staffCode;

    // 所属月份
    private String month;

    // 应发工资
    private Long shouldAmount;

    // 实发工资
    private Long factAmount;

    // 已发放金额
    private Long payAmount;

    // 补发金额
    private Long supplyAmount;

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

    // 月累积税费
    private Long totalTax;

    // 月累计扣款
    private Long totalCutAmount;

    // 共计发薪
    private Long totalAmount;

    // 用户类型
    private String kind;

    // 员工名称
    private String staffName;

    // 员工手机号
    private String staffMobile;

    // 状态类型
    private List<String> statusList;

    // 项目编号List
    private List<String> projectCodeList;

    // 月领薪人数
    private long number;

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
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

    public String getStaffMobile() {
        return staffMobile;
    }

    public void setStaffMobile(String staffMobile) {
        this.staffMobile = staffMobile;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public Long getSupplyAmount() {
        return supplyAmount;
    }

    public void setSupplyAmount(Long supplyAmount) {
        this.supplyAmount = supplyAmount;
    }

    public Long getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(Long totalTax) {
        this.totalTax = totalTax;
    }

    public Long getTotalCutAmount() {
        return totalCutAmount;
    }

    public void setTotalCutAmount(Long totalCutAmount) {
        this.totalCutAmount = totalCutAmount;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmunt(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

}
