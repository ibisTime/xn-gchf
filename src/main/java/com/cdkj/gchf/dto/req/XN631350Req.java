package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631350Req {

    // 公司编号 （必填）
    @NotBlank(message = "公司编号不能为空")
    private String companyCode;

    // 项目名称
    @NotBlank(message = "项目名称不能为空")
    private String name;

    // 负责人编号
    @NotBlank(message = "负责人编号不能为空")
    private String chargeUser;

    // 账户别称
    @NotBlank(message = "账户别称不能为空")
    private String bankCode;

    // 账户号
    @NotBlank(message = "账户号不能为空")
    private String bankcardNumber;

    // 银行名
    @NotBlank(message = "银行名不能为空")
    private String bankName;

    // 开户行
    @NotBlank(message = "开户行不能为空")
    private String subbranch;

    // 经度
    @NotBlank(message = "经度不能为空")
    private String longitude;

    // 纬度
    @NotBlank(message = "纬度不能为空")
    private String latitude;

    // 省
    @NotBlank(message = "省不能为空")
    private String province;

    // 市
    @NotBlank(message = "市不能为空")
    private String city;

    // 区
    @NotBlank(message = "区不能为空")
    private String area;

    // 地址
    @NotBlank(message = "地址不能为空")
    private String address;

    // 项目开始时间
    @NotBlank(message = "项目开始时间不能为空")
    private String startDatetime;

    // 薪资发放时间
    @NotBlank(message = "薪资发放时间不能为空")
    private String salaryDatetime;

    // 工资条形成时间
    @NotBlank(message = "工资条形成时间不能为空")
    private String salaryCreateDatetime;

    // 修改人
    @NotBlank(message = "修改人不能为空")
    private String updater;

    // 上班时间
    @NotBlank(message = "上班时间不能为空")
    private String attendanceStarttime;

    // 下班时间
    @NotBlank(message = "下班时间不能为空")
    private String attendanceEndtime;

    // 备注
    private String remark;

    public String getBankcardNumber() {
        return bankcardNumber;
    }

    public void setBankcardNumber(String bankcardNumber) {
        this.bankcardNumber = bankcardNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSubbranch() {
        return subbranch;
    }

    public void setSubbranch(String subbranch) {
        this.subbranch = subbranch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChargeUser() {
        return chargeUser;
    }

    public void setChargeUser(String chargeUser) {
        this.chargeUser = chargeUser;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(String startDatetime) {
        this.startDatetime = startDatetime;
    }

    public String getSalaryDatetime() {
        return salaryDatetime;
    }

    public void setSalaryDatetime(String salaryDatetime) {
        this.salaryDatetime = salaryDatetime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
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

    public String getSalaryCreateDatetime() {
        return salaryCreateDatetime;
    }

    public void setSalaryCreateDatetime(String salaryCreateDatetime) {
        this.salaryCreateDatetime = salaryCreateDatetime;
    }

}
