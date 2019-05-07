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
    // 是否关联
    private String isLink;

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

    public String getIsLink() {
        return isLink;
    }

    public void setIsLink(String isLink) {
        this.isLink = isLink;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((Specialty == null) ? 0 : Specialty.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result
                + ((archiveDatetime == null) ? 0 : archiveDatetime.hashCode());
        result = prime * result
                + ((bankLinkNumber == null) ? 0 : bankLinkNumber.hashCode());
        result = prime * result
                + ((bankName == null) ? 0 : bankName.hashCode());
        result = prime * result
                + ((cardNumber == null) ? 0 : cardNumber.hashCode());
        result = prime * result
                + ((cellPhone == null) ? 0 : cellPhone.hashCode());
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result
                + ((corpCode == null) ? 0 : corpCode.hashCode());
        result = prime * result
                + ((corpName == null) ? 0 : corpName.hashCode());
        result = prime * result + ((cultureLevelType == null) ? 0
                : cultureLevelType.hashCode());
        result = prime * result
                + ((cutAmount == null) ? 0 : cutAmount.hashCode());
        result = prime * result
                + ((deleteStatus == null) ? 0 : deleteStatus.hashCode());
        result = prime * result
                + ((deviceKey == null) ? 0 : deviceKey.hashCode());
        result = prime * result
                + ((endDatetime == null) ? 0 : endDatetime.hashCode());
        result = prime * result + ((entryAttachmentUrl == null) ? 0
                : entryAttachmentUrl.hashCode());
        result = prime * result
                + ((entryTime == null) ? 0 : entryTime.hashCode());
        result = prime * result + ((exitAttachmentUrl == null) ? 0
                : exitAttachmentUrl.hashCode());
        result = prime * result
                + ((exitTime == null) ? 0 : exitTime.hashCode());
        result = prime * result
                + ((expiryDate == null) ? 0 : expiryDate.hashCode());
        result = prime * result
                + ((grantOrg == null) ? 0 : grantOrg.hashCode());
        result = prime * result + ((hasBadMedicalHistory == null) ? 0
                : hasBadMedicalHistory.hashCode());
        result = prime * result
                + ((hasBuyInsurance == null) ? 0 : hasBuyInsurance.hashCode());
        result = prime * result
                + ((hasContract == null) ? 0 : hasContract.hashCode());
        result = prime * result
                + ((headImage == null) ? 0 : headImage.hashCode());
        result = prime * result
                + ((idcardNumber == null) ? 0 : idcardNumber.hashCode());
        result = prime * result
                + ((idcardType == null) ? 0 : idcardType.hashCode());
        result = prime * result + ((isLink == null) ? 0 : isLink.hashCode());
        result = prime * result
                + ((isTeamLeader == null) ? 0 : isTeamLeader.hashCode());
        result = prime * result
                + ((issueCardDate == null) ? 0 : issueCardDate.hashCode());
        result = prime * result
                + ((issueCardPicUrl == null) ? 0 : issueCardPicUrl.hashCode());
        result = prime * result
                + ((joinDatetime == null) ? 0 : joinDatetime.hashCode());
        result = prime * result
                + ((joinedTime == null) ? 0 : joinedTime.hashCode());
        result = prime * result
                + ((lastLeavingDays == null) ? 0 : lastLeavingDays.hashCode());
        result = prime * result
                + ((leavingDatetime == null) ? 0 : leavingDatetime.hashCode());
        result = prime * result
                + ((localTeamSysNo == null) ? 0 : localTeamSysNo.hashCode());
        result = prime * result
                + ((maritalStatus == null) ? 0 : maritalStatus.hashCode());
        result = prime * result + ((nation == null) ? 0 : nation.hashCode());
        result = prime * result + ((negativeIDCardImage == null) ? 0
                : negativeIDCardImage.hashCode());
        result = prime * result + ((payRollBankCardNumber == null) ? 0
                : payRollBankCardNumber.hashCode());
        result = prime * result
                + ((payRollBankName == null) ? 0 : payRollBankName.hashCode());
        result = prime * result + ((payRollTopBankCode == null) ? 0
                : payRollTopBankCode.hashCode());
        result = prime * result + ((payRollTopBankName == null) ? 0
                : payRollTopBankName.hashCode());
        result = prime * result
                + ((personGuid == null) ? 0 : personGuid.hashCode());
        result = prime * result
                + ((politicsType == null) ? 0 : politicsType.hashCode());
        result = prime * result
                + ((position == null) ? 0 : position.hashCode());
        result = prime * result + ((positiveIDCardImage == null) ? 0
                : positiveIDCardImage.hashCode());
        result = prime * result
                + ((projectCode == null) ? 0 : projectCode.hashCode());
        result = prime * result
                + ((projectName == null) ? 0 : projectName.hashCode());
        result = prime * result + ((realTeamMasterName == null) ? 0
                : realTeamMasterName.hashCode());
        result = prime * result + ((remark == null) ? 0 : remark.hashCode());
        result = prime * result + ((salary == null) ? 0 : salary.hashCode());
        result = prime * result
                + ((salaryStatus == null) ? 0 : salaryStatus.hashCode());
        result = prime * result
                + ((startDate == null) ? 0 : startDate.hashCode());
        result = prime * result
                + ((startDatetime == null) ? 0 : startDatetime.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result
                + ((teamName == null) ? 0 : teamName.hashCode());
        result = prime * result
                + ((teamSysNo == null) ? 0 : teamSysNo.hashCode());
        result = prime * result + ((totalLeavingDays == null) ? 0
                : totalLeavingDays.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result
                + ((updateDatetime == null) ? 0 : updateDatetime.hashCode());
        result = prime * result + ((updater == null) ? 0 : updater.hashCode());
        result = prime * result
                + ((uploadStatus == null) ? 0 : uploadStatus.hashCode());
        result = prime * result + ((uploadStatusList == null) ? 0
                : uploadStatusList.hashCode());
        result = prime * result
                + ((urgentLinkMan == null) ? 0 : urgentLinkMan.hashCode());
        result = prime * result + ((urgentLinkManPhone == null) ? 0
                : urgentLinkManPhone.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result
                + ((workDate == null) ? 0 : workDate.hashCode());
        result = prime * result
                + ((workRole == null) ? 0 : workRole.hashCode());
        result = prime * result
                + ((workType == null) ? 0 : workType.hashCode());
        result = prime * result
                + ((workerCode == null) ? 0 : workerCode.hashCode());
        result = prime * result
                + ((workerInfo == null) ? 0 : workerInfo.hashCode());
        result = prime * result
                + ((workerMobile == null) ? 0 : workerMobile.hashCode());
        result = prime * result
                + ((workerName == null) ? 0 : workerName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProjectWorker other = (ProjectWorker) obj;
        if (Specialty == null) {
            if (other.Specialty != null)
                return false;
        } else if (!Specialty.equals(other.Specialty))
            return false;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (archiveDatetime == null) {
            if (other.archiveDatetime != null)
                return false;
        } else if (!archiveDatetime.equals(other.archiveDatetime))
            return false;
        if (bankLinkNumber == null) {
            if (other.bankLinkNumber != null)
                return false;
        } else if (!bankLinkNumber.equals(other.bankLinkNumber))
            return false;
        if (bankName == null) {
            if (other.bankName != null)
                return false;
        } else if (!bankName.equals(other.bankName))
            return false;
        if (cardNumber == null) {
            if (other.cardNumber != null)
                return false;
        } else if (!cardNumber.equals(other.cardNumber))
            return false;
        if (cellPhone == null) {
            if (other.cellPhone != null)
                return false;
        } else if (!cellPhone.equals(other.cellPhone))
            return false;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (corpCode == null) {
            if (other.corpCode != null)
                return false;
        } else if (!corpCode.equals(other.corpCode))
            return false;
        if (corpName == null) {
            if (other.corpName != null)
                return false;
        } else if (!corpName.equals(other.corpName))
            return false;
        if (cultureLevelType == null) {
            if (other.cultureLevelType != null)
                return false;
        } else if (!cultureLevelType.equals(other.cultureLevelType))
            return false;
        if (cutAmount == null) {
            if (other.cutAmount != null)
                return false;
        } else if (!cutAmount.equals(other.cutAmount))
            return false;
        if (deleteStatus == null) {
            if (other.deleteStatus != null)
                return false;
        } else if (!deleteStatus.equals(other.deleteStatus))
            return false;
        if (deviceKey == null) {
            if (other.deviceKey != null)
                return false;
        } else if (!deviceKey.equals(other.deviceKey))
            return false;
        if (endDatetime == null) {
            if (other.endDatetime != null)
                return false;
        } else if (!endDatetime.equals(other.endDatetime))
            return false;
        if (entryAttachmentUrl == null) {
            if (other.entryAttachmentUrl != null)
                return false;
        } else if (!entryAttachmentUrl.equals(other.entryAttachmentUrl))
            return false;
        if (entryTime == null) {
            if (other.entryTime != null)
                return false;
        } else if (!entryTime.equals(other.entryTime))
            return false;
        if (exitAttachmentUrl == null) {
            if (other.exitAttachmentUrl != null)
                return false;
        } else if (!exitAttachmentUrl.equals(other.exitAttachmentUrl))
            return false;
        if (exitTime == null) {
            if (other.exitTime != null)
                return false;
        } else if (!exitTime.equals(other.exitTime))
            return false;
        if (expiryDate == null) {
            if (other.expiryDate != null)
                return false;
        } else if (!expiryDate.equals(other.expiryDate))
            return false;
        if (grantOrg == null) {
            if (other.grantOrg != null)
                return false;
        } else if (!grantOrg.equals(other.grantOrg))
            return false;
        if (hasBadMedicalHistory == null) {
            if (other.hasBadMedicalHistory != null)
                return false;
        } else if (!hasBadMedicalHistory.equals(other.hasBadMedicalHistory))
            return false;
        if (hasBuyInsurance == null) {
            if (other.hasBuyInsurance != null)
                return false;
        } else if (!hasBuyInsurance.equals(other.hasBuyInsurance))
            return false;
        if (hasContract == null) {
            if (other.hasContract != null)
                return false;
        } else if (!hasContract.equals(other.hasContract))
            return false;
        if (headImage == null) {
            if (other.headImage != null)
                return false;
        } else if (!headImage.equals(other.headImage))
            return false;
        if (idcardNumber == null) {
            if (other.idcardNumber != null)
                return false;
        } else if (!idcardNumber.equals(other.idcardNumber))
            return false;
        if (idcardType == null) {
            if (other.idcardType != null)
                return false;
        } else if (!idcardType.equals(other.idcardType))
            return false;
        if (isLink == null) {
            if (other.isLink != null)
                return false;
        } else if (!isLink.equals(other.isLink))
            return false;
        if (isTeamLeader == null) {
            if (other.isTeamLeader != null)
                return false;
        } else if (!isTeamLeader.equals(other.isTeamLeader))
            return false;
        if (issueCardDate == null) {
            if (other.issueCardDate != null)
                return false;
        } else if (!issueCardDate.equals(other.issueCardDate))
            return false;
        if (issueCardPicUrl == null) {
            if (other.issueCardPicUrl != null)
                return false;
        } else if (!issueCardPicUrl.equals(other.issueCardPicUrl))
            return false;
        if (joinDatetime == null) {
            if (other.joinDatetime != null)
                return false;
        } else if (!joinDatetime.equals(other.joinDatetime))
            return false;
        if (joinedTime == null) {
            if (other.joinedTime != null)
                return false;
        } else if (!joinedTime.equals(other.joinedTime))
            return false;
        if (lastLeavingDays == null) {
            if (other.lastLeavingDays != null)
                return false;
        } else if (!lastLeavingDays.equals(other.lastLeavingDays))
            return false;
        if (leavingDatetime == null) {
            if (other.leavingDatetime != null)
                return false;
        } else if (!leavingDatetime.equals(other.leavingDatetime))
            return false;
        if (localTeamSysNo == null) {
            if (other.localTeamSysNo != null)
                return false;
        } else if (!localTeamSysNo.equals(other.localTeamSysNo))
            return false;
        if (maritalStatus == null) {
            if (other.maritalStatus != null)
                return false;
        } else if (!maritalStatus.equals(other.maritalStatus))
            return false;
        if (nation == null) {
            if (other.nation != null)
                return false;
        } else if (!nation.equals(other.nation))
            return false;
        if (negativeIDCardImage == null) {
            if (other.negativeIDCardImage != null)
                return false;
        } else if (!negativeIDCardImage.equals(other.negativeIDCardImage))
            return false;
        if (payRollBankCardNumber == null) {
            if (other.payRollBankCardNumber != null)
                return false;
        } else if (!payRollBankCardNumber.equals(other.payRollBankCardNumber))
            return false;
        if (payRollBankName == null) {
            if (other.payRollBankName != null)
                return false;
        } else if (!payRollBankName.equals(other.payRollBankName))
            return false;
        if (payRollTopBankCode == null) {
            if (other.payRollTopBankCode != null)
                return false;
        } else if (!payRollTopBankCode.equals(other.payRollTopBankCode))
            return false;
        if (payRollTopBankName == null) {
            if (other.payRollTopBankName != null)
                return false;
        } else if (!payRollTopBankName.equals(other.payRollTopBankName))
            return false;
        if (personGuid == null) {
            if (other.personGuid != null)
                return false;
        } else if (!personGuid.equals(other.personGuid))
            return false;
        if (politicsType == null) {
            if (other.politicsType != null)
                return false;
        } else if (!politicsType.equals(other.politicsType))
            return false;
        if (position == null) {
            if (other.position != null)
                return false;
        } else if (!position.equals(other.position))
            return false;
        if (positiveIDCardImage == null) {
            if (other.positiveIDCardImage != null)
                return false;
        } else if (!positiveIDCardImage.equals(other.positiveIDCardImage))
            return false;
        if (projectCode == null) {
            if (other.projectCode != null)
                return false;
        } else if (!projectCode.equals(other.projectCode))
            return false;
        if (projectName == null) {
            if (other.projectName != null)
                return false;
        } else if (!projectName.equals(other.projectName))
            return false;
        if (realTeamMasterName == null) {
            if (other.realTeamMasterName != null)
                return false;
        } else if (!realTeamMasterName.equals(other.realTeamMasterName))
            return false;
        if (remark == null) {
            if (other.remark != null)
                return false;
        } else if (!remark.equals(other.remark))
            return false;
        if (salary == null) {
            if (other.salary != null)
                return false;
        } else if (!salary.equals(other.salary))
            return false;
        if (salaryStatus == null) {
            if (other.salaryStatus != null)
                return false;
        } else if (!salaryStatus.equals(other.salaryStatus))
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        if (startDatetime == null) {
            if (other.startDatetime != null)
                return false;
        } else if (!startDatetime.equals(other.startDatetime))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (teamName == null) {
            if (other.teamName != null)
                return false;
        } else if (!teamName.equals(other.teamName))
            return false;
        if (teamSysNo == null) {
            if (other.teamSysNo != null)
                return false;
        } else if (!teamSysNo.equals(other.teamSysNo))
            return false;
        if (totalLeavingDays == null) {
            if (other.totalLeavingDays != null)
                return false;
        } else if (!totalLeavingDays.equals(other.totalLeavingDays))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        if (updateDatetime == null) {
            if (other.updateDatetime != null)
                return false;
        } else if (!updateDatetime.equals(other.updateDatetime))
            return false;
        if (updater == null) {
            if (other.updater != null)
                return false;
        } else if (!updater.equals(other.updater))
            return false;
        if (uploadStatus == null) {
            if (other.uploadStatus != null)
                return false;
        } else if (!uploadStatus.equals(other.uploadStatus))
            return false;
        if (uploadStatusList == null) {
            if (other.uploadStatusList != null)
                return false;
        } else if (!uploadStatusList.equals(other.uploadStatusList))
            return false;
        if (urgentLinkMan == null) {
            if (other.urgentLinkMan != null)
                return false;
        } else if (!urgentLinkMan.equals(other.urgentLinkMan))
            return false;
        if (urgentLinkManPhone == null) {
            if (other.urgentLinkManPhone != null)
                return false;
        } else if (!urgentLinkManPhone.equals(other.urgentLinkManPhone))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        if (workDate == null) {
            if (other.workDate != null)
                return false;
        } else if (!workDate.equals(other.workDate))
            return false;
        if (workRole == null) {
            if (other.workRole != null)
                return false;
        } else if (!workRole.equals(other.workRole))
            return false;
        if (workType == null) {
            if (other.workType != null)
                return false;
        } else if (!workType.equals(other.workType))
            return false;
        if (workerCode == null) {
            if (other.workerCode != null)
                return false;
        } else if (!workerCode.equals(other.workerCode))
            return false;
        if (workerInfo == null) {
            if (other.workerInfo != null)
                return false;
        } else if (!workerInfo.equals(other.workerInfo))
            return false;
        if (workerMobile == null) {
            if (other.workerMobile != null)
                return false;
        } else if (!workerMobile.equals(other.workerMobile))
            return false;
        if (workerName == null) {
            if (other.workerName != null)
                return false;
        } else if (!workerName.equals(other.workerName))
            return false;
        return true;
    }

}
