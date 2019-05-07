package com.cdkj.gchf.domain;

import java.util.Date;
import java.util.List;

import com.cdkj.gchf.dao.base.ABaseDO;

/**
* 班组人员
* @author: xiongk 
* @since: 2019-03-16 10:44:02
* @history:
*/
public class ProjectWorker extends ABaseDO {

    private static final long serialVersionUID = -8538549658327391474L;

    // 编号
    private String code;

    // 项目编码
    private String projectCode;

    // 项目名称
    private String projectName;

    // 班组所在企业统一社会信用代码
    private String corpCode;

    // 班组所在企业名称
    private String corpName;

    // 班组编号
    private String teamSysNo;

    // 班组名称
    private String teamName;

    // 开始工作时间
    private Date workDate;

    // 员工编号
    private String workerCode;

    // 工人姓名
    private String workerName;

    // 员工手机号
    private String workerMobile;

    // 是否班组长
    private Integer isTeamLeader;

    // 证件类型
    private String idcardType;

    // 证件号码
    private String idcardNumber;

    // 工种
    private String workType;

    // 工人类型
    private Integer workRole;

    // 类别（直招工人，劳务工人，包工工人，内勤人员）
    private String type;

    // 进场时间
    private Date entryTime;

    // 退场时间
    private Date exitTime;

    // 进场确认附件
    private String entryAttachmentUrl;

    // 退场确认附件
    private String exitAttachmentUrl;

    // 制卡时间
    private Date issueCardDate;

    // 制卡采集照片
    private String issueCardPicUrl;

    // 考勤卡号
    private String cardNumber;

    // 发放工资银行卡号
    private String payRollBankCardNumber;

    // 发放工资银行名称
    private String payRollBankName;

    // 发放工资总行名称
    private String payRollTopBankName;

    // 工资卡银行联号
    private String bankLinkNumber;

    // 工资卡银行代码
    private String payRollTopBankCode;

    // 发放银行卡支行名称
    private String bankName;

    // 是否有劳动合同
    private Integer hasContract;

    // 有无购买工伤或意外伤害保险
    private Integer hasBuyInsurance;

    // 所在职位
    private String position;

    // 日薪
    private Long salary;

    // 扣款规则
    private Long cutAmount;

    // 状态
    private String status;

    // 工资发放状态
    private String salaryStatus;

    // 入职时间
    private Date joinDatetime;

    // 离职时间
    private Date leavingDatetime;

    // 最近一次请假开始时间
    private Date startDatetime;

    // 最近一次请假天数
    private Integer lastLeavingDays;

    // 最近一次请假结束时间
    private Date endDatetime;

    // 累积请假天数
    private Float totalLeavingDays;

    // 更新
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    /****DB Properties****/

    // 民族
    private String nation;

    // 地址
    private String address;

    // 头像
    private String headImage;

    // 政治面貌
    private String politicsType;

    // 加入公会时间
    private Date joinedTime;

    // 手机号码
    private String cellPhone;

    // 文化程度
    private String cultureLevelType;

    // 特长
    private String Specialty;

    // 是否有重大病史
    private String hasBadMedicalHistory;

    // 紧急联系人姓名
    private String urgentLinkMan;

    // 紧急联系方式
    private String urgentLinkManPhone;

    // 婚姻状况
    private String maritalStatus;

    // 发证机关
    private String grantOrg;

    // 正面照。
    private String positiveIDCardImage;

    // 反面照。
    private String negativeIDCardImage;

    // 证件有效期开始日期
    private String startDate;

    // 证件有效期结束日期
    private String expiryDate;

    // 状态
    private String uploadStatus;

    // 本地班组编号
    private String localTeamSysNo;

    // 删除状态
    private String deleteStatus;

    private String userId;

    // 建档时间
    private Date archiveDatetime;

    private WorkerInfo workerInfo;

    private List<String> uploadStatusList;

    private String deviceKey;

    // 实名制人员guid
    private String personGuid;

    /**
     * DB properties
     */
    private String realTeamMasterName;

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

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getTeamSysNo() {
        return teamSysNo;
    }

    public void setTeamSysNo(String teamSysNo) {
        this.teamSysNo = teamSysNo;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getWorkerCode() {
        return workerCode;
    }

    public void setWorkerCode(String workerCode) {
        this.workerCode = workerCode;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkerMobile() {
        return workerMobile;
    }

    public void setWorkerMobile(String workerMobile) {
        this.workerMobile = workerMobile;
    }

    public Integer getIsTeamLeader() {
        return isTeamLeader;
    }

    public void setIsTeamLeader(Integer isTeamLeader) {
        this.isTeamLeader = isTeamLeader;
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

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public Integer getWorkRole() {
        return workRole;
    }

    public void setWorkRole(Integer workRole) {
        this.workRole = workRole;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    public String getEntryAttachmentUrl() {
        return entryAttachmentUrl;
    }

    public void setEntryAttachmentUrl(String entryAttachmentUrl) {
        this.entryAttachmentUrl = entryAttachmentUrl;
    }

    public String getExitAttachmentUrl() {
        return exitAttachmentUrl;
    }

    public void setExitAttachmentUrl(String exitAttachmentUrl) {
        this.exitAttachmentUrl = exitAttachmentUrl;
    }

    public Date getIssueCardDate() {
        return issueCardDate;
    }

    public void setIssueCardDate(Date issueCardDate) {
        this.issueCardDate = issueCardDate;
    }

    public String getIssueCardPicUrl() {
        return issueCardPicUrl;
    }

    public void setIssueCardPicUrl(String issueCardPicUrl) {
        this.issueCardPicUrl = issueCardPicUrl;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPayRollBankCardNumber() {
        return payRollBankCardNumber;
    }

    public void setPayRollBankCardNumber(String payRollBankCardNumber) {
        this.payRollBankCardNumber = payRollBankCardNumber;
    }

    public String getPayRollBankName() {
        return payRollBankName;
    }

    public void setPayRollBankName(String payRollBankName) {
        this.payRollBankName = payRollBankName;
    }

    public String getPayRollTopBankName() {
        return payRollTopBankName;
    }

    public void setPayRollTopBankName(String payRollTopBankName) {
        this.payRollTopBankName = payRollTopBankName;
    }

    public String getBankLinkNumber() {
        return bankLinkNumber;
    }

    public void setBankLinkNumber(String bankLinkNumber) {
        this.bankLinkNumber = bankLinkNumber;
    }

    public String getPayRollTopBankCode() {
        return payRollTopBankCode;
    }

    public void setPayRollTopBankCode(String payRollTopBankCode) {
        this.payRollTopBankCode = payRollTopBankCode;
    }

    public Integer getHasContract() {
        return hasContract;
    }

    public void setHasContract(Integer hasContract) {
        this.hasContract = hasContract;
    }

    public Integer getHasBuyInsurance() {
        return hasBuyInsurance;
    }

    public void setHasBuyInsurance(Integer hasBuyInsurance) {
        this.hasBuyInsurance = hasBuyInsurance;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Long getCutAmount() {
        return cutAmount;
    }

    public void setCutAmount(Long cutAmount) {
        this.cutAmount = cutAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSalaryStatus() {
        return salaryStatus;
    }

    public void setSalaryStatus(String salaryStatus) {
        this.salaryStatus = salaryStatus;
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

    public Integer getLastLeavingDays() {
        return lastLeavingDays;
    }

    public void setLastLeavingDays(Integer lastLeavingDays) {
        this.lastLeavingDays = lastLeavingDays;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    public Float getTotalLeavingDays() {
        return totalLeavingDays;
    }

    public void setTotalLeavingDays(Float totalLeavingDays) {
        this.totalLeavingDays = totalLeavingDays;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getPoliticsType() {
        return politicsType;
    }

    public void setPoliticsType(String politicsType) {
        this.politicsType = politicsType;
    }

    public Date getJoinedTime() {
        return joinedTime;
    }

    public void setJoinedTime(Date joinedTime) {
        this.joinedTime = joinedTime;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getCultureLevelType() {
        return cultureLevelType;
    }

    public void setCultureLevelType(String cultureLevelType) {
        this.cultureLevelType = cultureLevelType;
    }

    public String getSpecialty() {
        return Specialty;
    }

    public void setSpecialty(String specialty) {
        Specialty = specialty;
    }

    public String getHasBadMedicalHistory() {
        return hasBadMedicalHistory;
    }

    public void setHasBadMedicalHistory(String hasBadMedicalHistory) {
        this.hasBadMedicalHistory = hasBadMedicalHistory;
    }

    public String getUrgentLinkMan() {
        return urgentLinkMan;
    }

    public void setUrgentLinkMan(String urgentLinkMan) {
        this.urgentLinkMan = urgentLinkMan;
    }

    public String getUrgentLinkManPhone() {
        return urgentLinkManPhone;
    }

    public void setUrgentLinkManPhone(String urgentLinkManPhone) {
        this.urgentLinkManPhone = urgentLinkManPhone;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getGrantOrg() {
        return grantOrg;
    }

    public void setGrantOrg(String grantOrg) {
        this.grantOrg = grantOrg;
    }

    public String getPositiveIDCardImage() {
        return positiveIDCardImage;
    }

    public void setPositiveIDCardImage(String positiveIDCardImage) {
        this.positiveIDCardImage = positiveIDCardImage;
    }

    public String getNegativeIDCardImage() {
        return negativeIDCardImage;
    }

    public void setNegativeIDCardImage(String negativeIDCardImage) {
        this.negativeIDCardImage = negativeIDCardImage;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public String getLocalTeamSysNo() {
        return localTeamSysNo;
    }

    public void setLocalTeamSysNo(String localTeamSysNo) {
        this.localTeamSysNo = localTeamSysNo;
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

    public Date getArchiveDatetime() {
        return archiveDatetime;
    }

    public void setArchiveDatetime(Date archiveDatetime) {
        this.archiveDatetime = archiveDatetime;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getRealTeamMasterName() {
        return realTeamMasterName;
    }

    public void setRealTeamMasterName(String realTeamMasterName) {
        this.realTeamMasterName = realTeamMasterName;
    }

    public WorkerInfo getWorkerInfo() {
        return workerInfo;
    }

    public void setWorkerInfo(WorkerInfo workerInfo) {
        this.workerInfo = workerInfo;
    }

    public List<String> getUploadStatusList() {
        return uploadStatusList;
    }

    public void setUploadStatusList(List<String> uploadStatusList) {
        this.uploadStatusList = uploadStatusList;
    }

    public String getDeviceKey() {
        return deviceKey;
    }

    public void setDeviceKey(String deviceKey) {
        this.deviceKey = deviceKey;
    }

    public String getPersonGuid() {
        return personGuid;
    }

    public void setPersonGuid(String personGuid) {
        this.personGuid = personGuid;
    }

}
