package com.cdkj.gchf.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cdkj.gchf.dao.base.ABaseDO;

/**
* 项目基本信息
* @author: xionk 
* @since: 2019-03-19 16:52:13
* @history:
*/
public class Project extends ABaseDO {

    private static final long serialVersionUID = -1083113402316735859L;

    // 编号
    private String code;

    // 总承包单位统一社会信用代码
    private String contractorCorpCode;

    // 总承包单位名称
    private String contractorCorpName;

    // 项目名称
    private String name;

    // 项目简介
    private String description;

    // 项目分类
    private String category;

    // 建设单位名称
    private String buildCorpName;

    // 建设单位统一社会信用代码
    private String buildCorpCode;

    // 建设用地规划许可证编号
    private String buildPlanNum;

    // 建设工程规划许可证编号
    private String prjPlanNum;

    // 项目所在地
    private String areaCode;

    // 总投资
    private BigDecimal invest;

    // 总面积
    private BigDecimal buildingArea;

    // 总长度
    private BigDecimal buildingLength;

    // 开工日期
    private Date startDate;

    // 竣工日期
    private Date completeDate;

    // 联系人姓名
    private String linkMan;

    // 联系人电话
    private String linkPhone;

    // 项目状态
    private String prjStatus;

    // 经度
    private BigDecimal lng;

    // 纬度
    private BigDecimal lat;

    // 项目地址
    private String address;

    // 立项文号
    private String approvalNum;

    // 立项级别
    private String approvalLevelNum;

    // 建设规模
    private String prjSize;

    // 建设性质
    private String propertyNum;

    // 工程用途
    private String prjNum;

    // 国籍或地区
    private String nationNum;

    // 第三方项目编码
    private String thirdPartyProjectCode;

    // 秘钥状态
    private String secretStatus;

    /****DB Properties****/
    // 操作人
    private String userId;

    // 项目许可证
    private List<ProjectBuilderLicense> projectBuilderLicenseList;

    // 全名
    private String fullName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContractorCorpCode() {
        return contractorCorpCode;
    }

    public void setContractorCorpCode(String contractorCorpCode) {
        this.contractorCorpCode = contractorCorpCode;
    }

    public String getContractorCorpName() {
        return contractorCorpName;
    }

    public void setContractorCorpName(String contractorCorpName) {
        this.contractorCorpName = contractorCorpName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBuildCorpName() {
        return buildCorpName;
    }

    public void setBuildCorpName(String buildCorpName) {
        this.buildCorpName = buildCorpName;
    }

    public String getBuildCorpCode() {
        return buildCorpCode;
    }

    public void setBuildCorpCode(String buildCorpCode) {
        this.buildCorpCode = buildCorpCode;
    }

    public String getBuildPlanNum() {
        return buildPlanNum;
    }

    public void setBuildPlanNum(String buildPlanNum) {
        this.buildPlanNum = buildPlanNum;
    }

    public String getPrjPlanNum() {
        return prjPlanNum;
    }

    public void setPrjPlanNum(String prjPlanNum) {
        this.prjPlanNum = prjPlanNum;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public BigDecimal getInvest() {
        return invest;
    }

    public void setInvest(BigDecimal invest) {
        this.invest = invest;
    }

    public BigDecimal getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(BigDecimal buildingArea) {
        this.buildingArea = buildingArea;
    }

    public BigDecimal getBuildingLength() {
        return buildingLength;
    }

    public void setBuildingLength(BigDecimal buildingLength) {
        this.buildingLength = buildingLength;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public String getPrjStatus() {
        return prjStatus;
    }

    public void setPrjStatus(String prjStatus) {
        this.prjStatus = prjStatus;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getApprovalNum() {
        return approvalNum;
    }

    public void setApprovalNum(String approvalNum) {
        this.approvalNum = approvalNum;
    }

    public String getApprovalLevelNum() {
        return approvalLevelNum;
    }

    public void setApprovalLevelNum(String approvalLevelNum) {
        this.approvalLevelNum = approvalLevelNum;
    }

    public String getPrjSize() {
        return prjSize;
    }

    public void setPrjSize(String prjSize) {
        this.prjSize = prjSize;
    }

    public String getPropertyNum() {
        return propertyNum;
    }

    public void setPropertyNum(String propertyNum) {
        this.propertyNum = propertyNum;
    }

    public String getPrjNum() {
        return prjNum;
    }

    public void setPrjNum(String prjNum) {
        this.prjNum = prjNum;
    }

    public String getNationNum() {
        return nationNum;
    }

    public void setNationNum(String nationNum) {
        this.nationNum = nationNum;
    }

    public String getThirdPartyProjectCode() {
        return thirdPartyProjectCode;
    }

    public void setThirdPartyProjectCode(String thirdPartyProjectCode) {
        this.thirdPartyProjectCode = thirdPartyProjectCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<ProjectBuilderLicense> getProjectBuilderLicenseList() {
        return projectBuilderLicenseList;
    }

    public void setProjectBuilderLicenseList(
            List<ProjectBuilderLicense> projectBuilderLicenseList) {
        this.projectBuilderLicenseList = projectBuilderLicenseList;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSecretStatus() {
        return secretStatus;
    }

    public void setSecretStatus(String secretStatus) {
        this.secretStatus = secretStatus;
    }

}
