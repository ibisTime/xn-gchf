package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 新增项目基本信息
 * @author: silver 
 * @since: Mar 13, 2019 1:34:10 PM 
 * @history:
 */
public class XN631600Req {

    // 总承包单位统一社会信用代码
    private String contractorCorpCode;

    // 总承包单位名称
    private String contractorCorpName;

    // 项目名称
    @NotBlank
    private String name;

    // 项目所在地
    @NotBlank
    private String areaCode;

    // 项目分类
    @NotBlank
    private String category;

    // 项目状态
    @NotBlank
    private String prjStatus;

    // 可用OCR数量
    @NotBlank
    private String totalOcrCount;

    // 项目简介
    private String description;

    // 建设单位统一社会信用代码
    private String buildCorpCode;

    // 建设单位名称
    private String buildCorpName;

    // 建设用地规划许可证编号
    private String buildPlanNum;

    // 建设工程规划许可证编号
    private String prjPlanNum;

    // 总投资
    private String invest;

    // 总面积
    private String buildingArea;

    // 总长度
    private String buildingLength;

    // 开工日期
    private String startDate;

    // 竣工日期
    private String completeDate;

    // 联系人姓名
    private String linkMan;

    // 联系人电话
    private String linkPhone;

    // 经度
    private String lng;

    // 纬度
    private String lat;

    // 省
    private String province;

    // 市
    private String city;

    // 区/县
    private String area;

    // 项目地址
    private String address;

    // 货币币种
    private String capitalCurrencyType;

    // 立项文号
    private String approvalNum;

    // 立项级别
    private String approvalLevelNum;

    // 建设规模
    private String prjSize;

    // 建设性质（property_num）
    private String propertyNum;

    // 工程用途（function_num）
    private String prjNum;

    // 国籍或地区
    private String nationNum;

    // 第三方项目编码
    private String thirdPartyProjectCode;

    // 上班时间
    private String attendanceStarttime;

    // 下班时间
    private String attendanceEndtime;

    // 工资条形成时间
    private String payRollCreateDatetime;

    // 薪资发放时间
    private String payRollDatetime;

    // 薪资发放可延迟天数
    private String payRollDelayDays;

    // 项目实名制负责人邮箱地址
    private String chargeEmail;

    // 操作用户
    @NotBlank
    private String userId;


    // 施工许可证
    private List<XN631600ReqLicenses> builderLicenses;

    public String getContractorCorpCode() {
        return contractorCorpCode;
    }

    public void setContractorCorpCode(String contractorCorpCode) {
        this.contractorCorpCode = contractorCorpCode;
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

    public List<XN631600ReqLicenses> getBuilderLicenses() {
        return builderLicenses;
    }

    public void setBuilderLicenses(List<XN631600ReqLicenses> builderLicenses) {
        this.builderLicenses = builderLicenses;
    }

    public String getInvest() {
        return invest;
    }

    public void setInvest(String invest) {
        this.invest = invest;
    }

    public String getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(String buildingArea) {
        this.buildingArea = buildingArea;
    }

    public String getBuildingLength() {
        return buildingLength;
    }

    public void setBuildingLength(String buildingLength) {
        this.buildingLength = buildingLength;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(String completeDate) {
        this.completeDate = completeDate;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getCapitalCurrencyType() {
        return capitalCurrencyType;
    }

    public void setCapitalCurrencyType(String capitalCurrencyType) {
        this.capitalCurrencyType = capitalCurrencyType;
    }

    public String getContractorCorpName() {
        return contractorCorpName;
    }

    public void setContractorCorpName(String contractorCorpName) {
        this.contractorCorpName = contractorCorpName;
    }

    public String getBuildCorpName() {
        return buildCorpName;
    }

    public void setBuildCorpName(String buildCorpName) {
        this.buildCorpName = buildCorpName;
    }

    public String getAttendanceStarttime() {
        return attendanceStarttime;
    }

    public void setAttendanceStarttime(String attendanceStarttime) {
        this.attendanceStarttime = attendanceStarttime;
    }

    public String getAttendanceEndtime() {
        return attendanceEndtime;
    }

    public void setAttendanceEndtime(String attendanceEndtime) {
        this.attendanceEndtime = attendanceEndtime;
    }

    public String getPayRollCreateDatetime() {
        return payRollCreateDatetime;
    }

    public void setPayRollCreateDatetime(String payRollCreateDatetime) {
        this.payRollCreateDatetime = payRollCreateDatetime;
    }

    public String getPayRollDatetime() {
        return payRollDatetime;
    }

    public void setPayRollDatetime(String payRollDatetime) {
        this.payRollDatetime = payRollDatetime;
    }

    public String getChargeEmail() {
        return chargeEmail;
    }

    public void setChargeEmail(String chargeEmail) {
        this.chargeEmail = chargeEmail;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPayRollDelayDays() {
        return payRollDelayDays;
    }

    public void setPayRollDelayDays(String payRollDelayDays) {
        this.payRollDelayDays = payRollDelayDays;
    }

    public String getTotalOcrCount() {
        return totalOcrCount;
    }

    public void setTotalOcrCount(String totalOcrCount) {
        this.totalOcrCount = totalOcrCount;
    }


}
