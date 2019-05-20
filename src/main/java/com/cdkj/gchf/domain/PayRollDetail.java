package com.cdkj.gchf.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.cdkj.gchf.dao.base.ABaseDO;

/**
* 人员工资单
* @author: xiongk 
* @since: 2019-03-16 11:23:10
* @history:
*/
public class PayRollDetail extends ABaseDO {

    private static final long serialVersionUID = 2070281773495945473L;

    // 编号
    private String code;

    // 工资单编码
    private String payRollCode;

    // 项目编码
    private String projectCode;

    /**
     * 员工编号
     */
    private String workerCode;

    // 工人姓名
    private String workerName;

    // 证件类型
    private String idcardType;

    // 证件号码
    private String idcardNumber;

    // 出勤天数
    private Integer days;

    // 总工时
    private BigDecimal workHours;

    // 工人工资卡号
    private String payRollBankCardNumber;

    // 工人工资卡银行代码
    private String payRollBankCode;

    // 工人工资卡开户行名称
    private String payRollBankName;

    // 工资代发银行卡号
    private String payBankCardNumber;

    // 工资代发银行代码
    private String payBankCode;

    // 工资代发开户行名称
    private String payBankName;

    // 应发金额
    private BigDecimal totalPayAmount;

    // 实发金额
    private BigDecimal actualAmount;

    // 是否是补发
    private Integer isBackPay;

    // 发放日期
    private Date balanceDate;

    // 第三方工资单编号
    private String thirdPayRollCode;

    // 补发日期
    private Date backPayMonth;

    /****DB Properties****/
    // 项目名称
    private String projectName;

    // 班组名称
    private String teamName;

    // 企业名称
    private String corpName;

    // 企业编号
    private String corpCode;

    // 状态
    private String uploadStatus;

    private String deleteStatus;

    private String userId;

    // 业主端查询
    private String ownerProjectCode;

    // 发放月份
    private Date payMonth;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPayRollCode() {
        return payRollCode;
    }

    public void setPayRollCode(String payRollCode) {
        this.payRollCode = payRollCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getIdcardType() {
        return idcardType;
    }

    public void setIdcardType(String idcardType) {
        this.idcardType = idcardType;
    }

    public String getIdcardNumber() {
        return idcardNumber;
    }

    public void setIdcardNumber(String idcardNumber) {
        this.idcardNumber = idcardNumber;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public BigDecimal getWorkHours() {
        return workHours;
    }

    public void setWorkHours(BigDecimal workHours) {
        this.workHours = workHours;
    }

    public String getPayRollBankCardNumber() {
        return payRollBankCardNumber;
    }

    public void setPayRollBankCardNumber(String payRollBankCardNumber) {
        this.payRollBankCardNumber = payRollBankCardNumber;
    }

    public String getPayRollBankCode() {
        return payRollBankCode;
    }

    public void setPayRollBankCode(String payRollBankCode) {
        this.payRollBankCode = payRollBankCode;
    }

    public String getPayRollBankName() {
        return payRollBankName;
    }

    public void setPayRollBankName(String payRollBankName) {
        this.payRollBankName = payRollBankName;
    }

    public String getPayBankCardNumber() {
        return payBankCardNumber;
    }

    public void setPayBankCardNumber(String payBankCardNumber) {
        this.payBankCardNumber = payBankCardNumber;
    }

    public String getPayBankCode() {
        return payBankCode;
    }

    public void setPayBankCode(String payBankCode) {
        this.payBankCode = payBankCode;
    }

    public String getPayBankName() {
        return payBankName;
    }

    public void setPayBankName(String payBankName) {
        this.payBankName = payBankName;
    }

    public BigDecimal getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(BigDecimal totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Integer getIsBackPay() {
        return isBackPay;
    }

    public void setIsBackPay(Integer isBackPay) {
        this.isBackPay = isBackPay;
    }

    public Date getBalanceDate() {
        return balanceDate;
    }

    public void setBalanceDate(Date balanceDate) {
        this.balanceDate = balanceDate;
    }

    public String getThirdPayRollCode() {
        return thirdPayRollCode;
    }

    public void setThirdPayRollCode(String thirdPayRollCode) {
        this.thirdPayRollCode = thirdPayRollCode;
    }

    public Date getBackPayMonth() {
        return backPayMonth;
    }

    public void setBackPayMonth(Date backPayMonth) {
        this.backPayMonth = backPayMonth;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

    public String getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public String getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(String deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getPayMonth() {
        return payMonth;
    }

    public void setPayMonth(Date payMonth) {
        this.payMonth = payMonth;
    }

    public String getOwnerProjectCode() {
        return ownerProjectCode;
    }

    public void setOwnerProjectCode(String ownerProjectCode) {
        this.ownerProjectCode = ownerProjectCode;
    }

    public String getWorkerCode() {
        return workerCode;
    }

    public void setWorkerCode(String workerCode) {
        this.workerCode = workerCode;
    }
}
