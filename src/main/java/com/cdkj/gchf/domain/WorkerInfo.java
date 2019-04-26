package com.cdkj.gchf.domain;

import java.util.Date;

import com.cdkj.gchf.dao.base.ABaseDO;

/**
* 人员实名信息
* @author: jiafr 
* @since: 2019-03-25 14:31:26
* @history:
*/
public class WorkerInfo extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 工人姓名
    private String name;

    // 证件类型
    private String idCardType;

    // 证件号码
    private String idCardNumber;

    // 工人性别
    private Integer gender;

    // 民族
    private String nation;

    // 学历
    private String eduLevel;

    // 学位
    private Integer degree;

    // 类别
    private String workerType;

    // 出生日期
    private Date birthday;

    // 籍贯 身份证号码前六位
    private String birthPlaceCode;

    // 住址
    private String address;

    // 头像 二代身份证上面的头像
    private String headImageUrl;

    // 政治面貌
    private String politicsType;

    // 是否加入工会
    private Integer isJoined;

    // 加入工会时间 已加入工会时，此字段必须有值
    private Date joinedTime;

    // 手机号码
    private String cellPhone;

    // 文化程度
    private String cultureLevelType;

    // 特长
    private String specialty;

    // 否有重大病史
    private Integer hasBadMedicalHistory;

    // 紧急联系人姓名
    private String urgentLinkMan;

    // 紧急联系电话
    private String urgentLinkManPhone;

    // 当前工种
    private String workTypeCode;

    // 当前聘用企业
    private String workCorpName;

    // 开始工作日期
    private Date workDate;

    // 婚姻状况
    private String maritalStatus;

    // 发证机关
    private String grantOrg;

    // 正面照 URL
    private String positiveIdCardImageUrl;

    // 反面照 URL
    private String negativeIdCardImageUrl;

    // 手持身份证照片
    private String handIdCardImageUrl;

    // 有效期开始日期
    private Date startDate;

    // 有效期结束日期
    private Date expiryDate;

    // 建档时间
    private Date createDatetime;

    /****DB Properties****/

    // 建档开始时间
    private Date createDatetimeStart;

    // 建档结束时间
    private Date createDatetimeEnd;

    // 项目端人员身份证号
    private String businessIdCardNumber;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getEduLevel() {
        return eduLevel;
    }

    public void setEduLevel(String eduLevel) {
        this.eduLevel = eduLevel;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public String getWorkerType() {
        return workerType;
    }

    public void setWorkerType(String workerType) {
        this.workerType = workerType;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getBirthPlaceCode() {
        return birthPlaceCode;
    }

    public void setBirthPlaceCode(String birthPlaceCode) {
        this.birthPlaceCode = birthPlaceCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }

    public String getPoliticsType() {
        return politicsType;
    }

    public void setPoliticsType(String politicsType) {
        this.politicsType = politicsType;
    }

    public Integer getIsJoined() {
        return isJoined;
    }

    public void setIsJoined(Integer isJoined) {
        this.isJoined = isJoined;
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
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Integer getHasBadMedicalHistory() {
        return hasBadMedicalHistory;
    }

    public void setHasBadMedicalHistory(Integer hasBadMedicalHistory) {
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

    public String getWorkTypeCode() {
        return workTypeCode;
    }

    public void setWorkTypeCode(String workTypeCode) {
        this.workTypeCode = workTypeCode;
    }

    public String getWorkCorpName() {
        return workCorpName;
    }

    public void setWorkCorpName(String workCorpName) {
        this.workCorpName = workCorpName;
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

    public String getPositiveIdCardImageUrl() {
        return positiveIdCardImageUrl;
    }

    public void setPositiveIdCardImageUrl(String positiveIdCardImageUrl) {
        this.positiveIdCardImageUrl = positiveIdCardImageUrl;
    }

    public String getNegativeIdCardImageUrl() {
        return negativeIdCardImageUrl;
    }

    public void setNegativeIdCardImageUrl(String negativeIdCardImageUrl) {
        this.negativeIdCardImageUrl = negativeIdCardImageUrl;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getHandIdCardImageUrl() {
        return handIdCardImageUrl;
    }

    public void setHandIdCardImageUrl(String handIdCardImageUrl) {
        this.handIdCardImageUrl = handIdCardImageUrl;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getCreateDatetimeStart() {
        return createDatetimeStart;
    }

    public void setCreateDatetimeStart(Date createDatetimeStart) {
        this.createDatetimeStart = createDatetimeStart;
    }

    public Date getCreateDatetimeEnd() {
        return createDatetimeEnd;
    }

    public void setCreateDatetimeEnd(Date createDatetimeEnd) {
        this.createDatetimeEnd = createDatetimeEnd;
    }

    public String getBusinessIdCardNumber() {
        return businessIdCardNumber;
    }

    public void setBusinessIdCardNumber(String businessIdCardNumber) {
        this.businessIdCardNumber = businessIdCardNumber;
    }

}
